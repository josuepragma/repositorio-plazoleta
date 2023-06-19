package com.pragma.smallsquare.domain.api;

import com.pragma.smallsquare.domain.model.Order;

import java.util.List;

public interface IOrderServicePort {

    void saveOrder(Order order);

    Order getLastOrderByCustomerId(Integer customerId);

    List<Order> getAllOrdersFilteredByStatus(String status, int page, int size);
}
