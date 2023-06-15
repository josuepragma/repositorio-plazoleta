package com.pragma.smallsquare.domain.spi;

import com.pragma.smallsquare.domain.model.Order;

public interface IOrderPersistencePort {

    void saveOrder(Order order);

    Order getOrderById(Integer id);

    void updateOrder(Order order);
}
