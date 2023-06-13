package com.pragma.smallsquare.restaurant.domain.spi;

import com.pragma.smallsquare.restaurant.domain.model.Dish;

import java.util.List;

public interface IDishPersistencePort {
    void saveDish(Dish dish);

    List<Dish> getAllDishes();

    Dish getDishById(Integer id);

    Dish getDishByName(String name);

    void updateDish(Dish dish);

    void deleteDishById(Integer id);
}
