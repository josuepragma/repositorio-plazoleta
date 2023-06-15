package com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.repository;

import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.entity.CategoryEntity;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.entity.DishEntity;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity, Integer> {

    Optional<DishEntity> findByName(String dishName);

    List<DishEntity> findAllByRestaurantAndCategory(RestaurantEntity restaurant,
                                                    CategoryEntity category,
                                                    Pageable pageable);
}
