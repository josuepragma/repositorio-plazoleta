package com.pragma.smallsquare.domain.api;

import com.pragma.smallsquare.domain.model.Order;

public interface IOrderServicePort {

    void saveOrder(Order order);

    Order getOrderById(Integer id);

    void updateOrder(Order order);

}
