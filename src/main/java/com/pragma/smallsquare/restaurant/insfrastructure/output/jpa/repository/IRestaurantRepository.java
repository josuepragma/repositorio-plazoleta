package com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.repository;

import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {

    Optional<RestaurantEntity> findByName(String restaurantName);

    Optional<RestaurantEntity> findByNit(String restaurantNit);

    void deleteByNit(String restaurantNit);
}
