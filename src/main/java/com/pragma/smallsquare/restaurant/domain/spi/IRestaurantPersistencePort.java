package com.pragma.smallsquare.restaurant.domain.spi;

import com.pragma.smallsquare.restaurant.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantPersistencePort {
    Restaurant saveRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurantByNit(String nit);

    Restaurant getRestaurantByName(String name);

    Restaurant updateRestaurant(Restaurant restaurant);

    void deleteRestaurantByNit(String nit);
}
