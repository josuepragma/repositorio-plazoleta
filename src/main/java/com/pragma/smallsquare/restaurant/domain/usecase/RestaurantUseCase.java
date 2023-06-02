package com.pragma.smallsquare.restaurant.domain.usecase;

import com.pragma.smallsquare.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.restaurant.domain.model.Restaurant;
import com.pragma.smallsquare.restaurant.domain.spi.IRestaurantPersistencePort;

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
    public List<Restaurant> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }

    @Override
    public Restaurant getRestaurantByNit(String nit) {
        return restaurantPersistencePort.getRestaurantByNit(nit);
    }

    @Override
    public Restaurant getRestaurantByName(String name) {
        return restaurantPersistencePort.getRestaurantByName(name);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        return restaurantPersistencePort.updateRestaurant(restaurant);
    }

    @Override
    public void deleteRestaurantByNit(String nit) {
        restaurantPersistencePort.deleteRestaurantByNit(nit);
    }
}
