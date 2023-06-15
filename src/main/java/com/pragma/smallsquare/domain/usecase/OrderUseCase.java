package com.pragma.smallsquare.domain.usecase;

import com.pragma.smallsquare.domain.api.IOrderServicePort;
import com.pragma.smallsquare.domain.model.Order;
import com.pragma.smallsquare.domain.spi.IOrderPersistencePort;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public void saveOrder(Order order) {
        orderPersistencePort.saveOrder(order);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderPersistencePort.getOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderPersistencePort.updateOrder(order);
    }
}
