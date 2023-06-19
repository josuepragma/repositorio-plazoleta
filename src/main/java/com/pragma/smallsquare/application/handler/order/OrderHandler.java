package com.pragma.smallsquare.application.handler.order;

import com.pragma.smallsquare.application.dto.request.OrderRequest;
import com.pragma.smallsquare.application.dto.response.OrderResponse;
import com.pragma.smallsquare.application.exceptions.OrderStatusDoesNotExistException;
import com.pragma.smallsquare.application.exceptions.OrderStatusInProgressException;
import com.pragma.smallsquare.application.mapper.IOrderDishRequestMapper;
import com.pragma.smallsquare.application.mapper.IOrderRequestMapper;
import com.pragma.smallsquare.application.mapper.IOrderResponseMapper;
import com.pragma.smallsquare.insfrastructure.util.StatusEnum;
import com.pragma.smallsquare.domain.api.IOrderServicePort;
import com.pragma.smallsquare.domain.model.Order;
import com.pragma.smallsquare.domain.model.OrderDish;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderDishRequestMapper orderDishRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;

    @Override
    public void saveNewOrderDto(OrderRequest orderRequest, Integer currentCustomerId) {
        log.warn("<--- FROM ORDER HANDLER --->");
        Order lastOrder = orderServicePort.getLastOrderByCustomerId(currentCustomerId);
        if (lastOrder != null) {
            String status = lastOrder.getStatus();
            if (status.equals(StatusEnum.PENDING.getName()) ||
                    status.equals(StatusEnum.IN_PREPARATION.getName()) ||
                    status.equals(StatusEnum.READY.getName())) {
                throw new OrderStatusInProgressException("CURRENT ORDER cannot be taken, because you currently have an ORDER in status: " + status);
            }
        }

        Order order = orderRequestMapper.toOrder(orderRequest);
        order.setIdCustomer(currentCustomerId);
        order.setOrderDate(new Date());
        order.setStatus(StatusEnum.PENDING.getName());

        List<OrderDish> orderDishList = orderDishRequestMapper.toOrderDishList(orderRequest.getOrdersDishes());
        for (OrderDish item : orderDishList) {
            log.warn("<--- ID DISH = " + item.getIdDish() + "| QUANTITY = " + item.getQuantity() + " --->");
            order.getOrdersDishes().add(item);
        }

        orderServicePort.saveOrder(order);
    }

    @Override
    public List<OrderResponse> getAllOrdersFilteredByStatus(String status, int page, int size) {

        if (!StatusEnum.statusExists(status)) {
            throw new OrderStatusDoesNotExistException("STATUS entered does not exist");
        }

        List<Order> orders = orderServicePort.getAllOrdersFilteredByStatus(status, page, size);
        return orderResponseMapper.toOrderResponseList(orders);
    }
}
