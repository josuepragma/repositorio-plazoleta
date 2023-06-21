package com.pragma.smallsquare.domain.api;

import com.pragma.smallsquare.domain.model.Restaurant;

import java.util.List;

public interface IRestaurantServicePort {
    Restaurant saveRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurantsOrderByName(int startPage, int size);

    Restaurant getRestaurantById(Integer id);

    Restaurant getRestaurantByIdOwner(Integer idOwner);
}
