package com.pragma.smallsquare.domain.usecase;

import com.pragma.smallsquare.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.domain.spi.IRestaurantPersistencePort;

import java.util.List;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        return restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurantsOrderByName(int startPage, int size) {
        return restaurantPersistencePort.getAllRestaurantsOrderByName(startPage, size);
    }

    @Override
    public Restaurant getRestaurantById(Integer id) {
        return restaurantPersistencePort.getRestaurantById(id);
    }

    @Override
    public Restaurant getRestaurantByIdOwner(Integer idOwner) {
        return restaurantPersistencePort.getRestaurantByIdOwner(idOwner);
    }

}
