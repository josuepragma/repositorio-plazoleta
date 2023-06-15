package com.pragma.smallsquare.domain.spi;

import com.pragma.smallsquare.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {
    Restaurant saveRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurantsOrderByName(int startPage, int size);

    Restaurant getRestaurantById(Integer id);
}
