package com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.adapter;

import com.pragma.smallsquare.restaurant.domain.model.Restaurant;
import com.pragma.smallsquare.restaurant.domain.spi.IRestaurantPersistencePort;
import com.pragma.smallsquare.restaurant.insfrastructure.exceptions.NoDataFoundException;
import com.pragma.smallsquare.restaurant.insfrastructure.exceptions.RestaurantAlreadyExistsException;
import com.pragma.smallsquare.restaurant.insfrastructure.exceptions.RestaurantNotFoundException;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {
        if (restaurantRepository.findByNit(restaurant.getNit()).isPresent()) {
            throw new RestaurantAlreadyExistsException();
        }

        RestaurantEntity restaurantEntity = restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
        return restaurantEntityMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<RestaurantEntity> restaurantEntityList = restaurantRepository.findAll();

        if (restaurantEntityList.isEmpty()) {
            throw new NoDataFoundException("Restaurant List is empty");
        }

        return restaurantEntityMapper.toRestaurantList(restaurantEntityList);
    }

    @Override
    public Restaurant getRestaurantByNit(String nit) {

        RestaurantEntity restaurantEntity = restaurantRepository.findByNit(nit)
                .orElseThrow(RestaurantNotFoundException::new);

        return restaurantEntityMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public Restaurant getRestaurantByName(String name) {
        RestaurantEntity restaurantEntity = restaurantRepository.findByName(name)
                .orElseThrow(RestaurantNotFoundException::new);

        return restaurantEntityMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant restaurant) {
        RestaurantEntity restaurantEntity = restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));

        return restaurantEntityMapper.toRestaurant(restaurantEntity);
    }

    @Override
    public void deleteRestaurantByNit(String nit) {
        restaurantRepository.deleteByNit(nit);
    }
}
