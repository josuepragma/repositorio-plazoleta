package com.pragma.smallsquare.application.handler.order;

import com.pragma.smallsquare.application.dto.request.OrderInPreparationRequest;
import com.pragma.smallsquare.application.dto.request.OrderReadyRequest;
import com.pragma.smallsquare.application.dto.request.OrderRequest;
import com.pragma.smallsquare.application.dto.response.OrderResponse;
import com.pragma.smallsquare.application.exceptions.*;
import com.pragma.smallsquare.application.mapper.IOrderDishRequestMapper;
import com.pragma.smallsquare.application.mapper.IOrderRequestMapper;
import com.pragma.smallsquare.application.mapper.IOrderResponseMapper;
import com.pragma.smallsquare.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.domain.api.employee.IEmployeeRestaurantServicePort;
import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.domain.model.employee.EmployeeRestaurant;
import com.pragma.smallsquare.insfrastructure.output.twilio.TwilioSmsSender;
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
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;

    private final IOrderDishRequestMapper orderDishRequestMapper;

    private final IEmployeeRestaurantServicePort employeeRestaurantServicePort;
    private final IRestaurantServicePort restaurantServicePort;

    private final TwilioSmsSender twilioSmsSender;

    @Override
    public void saveNewOrderDto(OrderRequest orderRequest, Integer currentCustomerId) {
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
        order.setIdChef(null);
        order.setIdCustomer(currentCustomerId);
        order.setOrderDate(new Date());
        order.setStatus(StatusEnum.PENDING.getName());

        List<OrderDish> orderDishList = orderDishRequestMapper.toOrderDishList(orderRequest.getOrdersDishes());
        for (OrderDish item : orderDishList) {
            order.getOrdersDishes().add(item);
        }

        orderServicePort.saveOrder(order);
    }

    @Override
    public List<OrderResponse> getAllOrdersFilteredByStatusAndRestaurant(String status, int page, int size, Integer currentEmployeeId) {
        if (!StatusEnum.statusExists(status)) {
            throw new OrderStatusDoesNotExistException("STATUS entered does not exist");
        }

        EmployeeRestaurant employeeRestaurant = employeeRestaurantServicePort.getEmployeeRestaurantByIdEmployee(currentEmployeeId);
        Restaurant restaurant = restaurantServicePort.getRestaurantById(employeeRestaurant.getIdRestaurant());

        List<Order> orders = orderServicePort.getAllOrdersFilteredByStatusAndRestaurant(status, page, size, restaurant);

        return orderResponseMapper.toOrderResponseList(orders);
    }

    @Override
    public void updateOrderInPreparationStatus(OrderInPreparationRequest orderRequest, Integer orderId, Integer currentEmployeeId) {

        if (!Objects.equals(orderRequest.getIdChef(), currentEmployeeId)) {
            throw new InvalidAssignedEmployeeException("IdChef assigned is different to current Chef");
        }

        if (!orderRequest.getStatus().equals(StatusEnum.IN_PREPARATION.getName())) {
            throw new InPreparationStatusException("Status should be 'EN_PREPARACION'");
        }

        Order updatedOrder = orderServicePort.getOrderById(orderId);

        EmployeeRestaurant employeeRestaurant = employeeRestaurantServicePort.getEmployeeRestaurantByIdEmployee(currentEmployeeId);
        if (!employeeRestaurant.getIdRestaurant().equals(updatedOrder.getRestaurant().getId())) {
            throw new UnauthorizedUserException("Current Employee cannot assigned orders from others Restaurants");
        }

        updatedOrder.setIdChef(orderRequest.getIdChef());
        updatedOrder.setStatus(orderRequest.getStatus());

        orderServicePort.saveOrder(updatedOrder);
    }

    @Override
    public void updateOrderReadyStatus(OrderReadyRequest orderRequest, Integer orderId, Integer currentEmployeeId) {
        Order updatedOrder = orderServicePort.getOrderById(orderId);

        updatedOrder.setStatus(orderRequest.getStatus());

        orderServicePort.saveOrder(updatedOrder);

        twilioSmsSender.sendSms("+51940840990",
                "Status Order: " + orderRequest.getStatus() + " | Security PIN: " + orderRequest.getSecurityPin());
    }
}
