package com.pragma.smallsquare.insfrastructure.output.jpa.adapter;

import com.pragma.smallsquare.domain.model.Order;
import com.pragma.smallsquare.domain.spi.IOrderPersistencePort;
import com.pragma.smallsquare.insfrastructure.output.jpa.entity.OrderDishEntity;
import com.pragma.smallsquare.insfrastructure.output.jpa.entity.OrderEntity;
import com.pragma.smallsquare.insfrastructure.output.jpa.mapper.IOrderEntityMapper;
import com.pragma.smallsquare.insfrastructure.output.jpa.repository.IOrderRepository;
import com.pragma.smallsquare.insfrastructure.util.StatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public void saveOrder(Order order) {
        log.warn("<--- FROM ORDER JPA ADAPTER --->");
        OrderEntity orderEntity = orderEntityMapper.toOrderEntity(order);

        for (OrderDishEntity item : orderEntity.getOrdersDishes()) {
            log.warn("<--- ID DISH = " + item.getIdDish() + "| QUANTITY = " + item.getQuantity() + " --->");
            item.setOrder(orderEntity);
        }
        orderRepository.save(orderEntity);
    }

    @Override
    public Order getLastOrderByCustomerId(Integer customerId) {
        log.warn("<--- FROM ORDER JPA ADAPTER --->");
        Pageable pageable = PageRequest.of(0, 1);

        log.warn("<--- getLastOrderByCustomerId(Integer customerId) --->");
        if (orderRepository.findLastOrderByCustomerId(customerId, pageable).isEmpty()) {
            log.warn("<--- IF orderEntity == null --->");
            return null;
        }

        return orderEntityMapper.toOrder(orderRepository.findLastOrderByCustomerId(customerId, pageable).get(0));
    }

    @Override
    public List<Order> getAllOrdersFilteredByStatus(String status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        List<OrderEntity> orderEntityList = orderRepository.findAllByStatus(status, pageable);
        return orderEntityMapper.toOrderList(orderEntityList);
    }

}
