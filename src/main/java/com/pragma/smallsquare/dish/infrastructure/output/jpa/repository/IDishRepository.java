package com.pragma.smallsquare.dish.infrastructure.output.jpa.repository;

import com.pragma.smallsquare.dish.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity, Integer> {

    Optional<DishEntity> findByName(String dishName);
}
