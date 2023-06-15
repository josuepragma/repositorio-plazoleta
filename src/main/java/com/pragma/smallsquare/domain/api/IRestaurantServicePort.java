package com.pragma.smallsquare.restaurant.domain.api;

import com.pragma.smallsquare.restaurant.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {
    Restaurant saveRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    List<Restaurant> getAllRestaurantsOrderByName(int startPage, int size);

    Restaurant getRestaurantById(Integer id);

    Restaurant getRestaurantByNit(String nit);

    Restaurant updateRestaurant(Restaurant restaurant);

    void deleteRestaurantById(Integer id);
}
