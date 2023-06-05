package com.pragma.smallsquare.restaurant.domain.spi;

import com.pragma.smallsquare.restaurant.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {
    Restaurant saveRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurantById(Integer id);

    Restaurant getRestaurantByNit(String nit);

    Restaurant updateRestaurant(Restaurant restaurant);

    void deleteRestaurantById(Integer id);
}
