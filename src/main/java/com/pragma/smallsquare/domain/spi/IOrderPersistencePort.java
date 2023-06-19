package com.pragma.smallsquare.domain.spi;

import com.pragma.smallsquare.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {

    void saveOrder(Order order);

    Order getLastOrderByCustomerId(Integer customerId);

    List<Order> getAllOrdersFilteredByStatus(String status, int page, int size);

}
