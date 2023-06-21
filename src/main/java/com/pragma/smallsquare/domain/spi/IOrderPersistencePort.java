package com.pragma.smallsquare.domain.spi;

import com.pragma.smallsquare.domain.model.Order;
import com.pragma.smallsquare.domain.model.Restaurant;

import java.util.List;

public interface IOrderPersistencePort {

    void saveOrder(Order order);

    Order getLastOrderByCustomerId(Integer customerId);

    List<Order> getAllOrdersFilteredByStatusAndRestaurant(String status, int page, int size, Restaurant restaurant);

}
