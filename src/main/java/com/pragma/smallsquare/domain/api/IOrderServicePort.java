package com.pragma.smallsquare.domain.api;

import com.pragma.smallsquare.domain.model.Order;
import com.pragma.smallsquare.domain.model.Restaurant;

import java.util.List;

public interface IOrderServicePort {

    void saveOrder(Order order);

    Order getLastOrderByCustomerId(Integer customerId);

    List<Order> getAllOrdersFilteredByStatusAndRestaurant(String status, int page, int size, Restaurant restaurant);

    Order getOrderById(Integer id);

    void updateEmployeeIdAndStatus(Order order);
}
