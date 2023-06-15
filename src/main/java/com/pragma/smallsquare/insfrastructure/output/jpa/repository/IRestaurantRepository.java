package com.pragma.smallsquare.insfrastructure.output.jpa.repository;

import com.pragma.smallsquare.insfrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {

    Optional<RestaurantEntity> findByName(String restaurantName);

    Optional<RestaurantEntity> findByNit(String restaurantNit);

    List<RestaurantEntity> findAllByOrderByName(Pageable pageable);

    void deleteByNit(String restaurantNit);
}
