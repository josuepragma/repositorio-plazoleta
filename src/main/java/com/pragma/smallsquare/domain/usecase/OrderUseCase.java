package com.pragma.smallsquare.domain.usecase;

import com.pragma.smallsquare.domain.api.IOrderServicePort;
import com.pragma.smallsquare.domain.model.Order;
import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.domain.spi.IOrderPersistencePort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public void saveOrder(Order order) {
        log.warn("<--- FROM ORDER USE CASE --->");
        log.warn("<--- orderPersistencePort.saveOrder(order); --->");
        orderPersistencePort.saveOrder(order);
    }

    @Override
    public Order getLastOrderByCustomerId(Integer customerId) {
        return orderPersistencePort.getLastOrderByCustomerId(customerId);
    }

    @Override
    public List<Order> getAllOrdersFilteredByStatusAndRestaurant(String status, int page, int size, Restaurant restaurant) {
        return orderPersistencePort.getAllOrdersFilteredByStatusAndRestaurant(status, page, size, restaurant);
    }
}
