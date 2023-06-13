package com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.repository;

import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity, Integer> {

    Optional<DishEntity> findByName(String dishName);
}
