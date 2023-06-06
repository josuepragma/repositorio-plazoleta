package com.pragma.smallsquare.dish.domain.api;

import com.pragma.smallsquare.dish.domain.model.Dish;

import java.util.List;

public interface IDishServicePort {
    void saveDish(Dish dish);

    List<Dish> getAllDishes();

    Dish getDishById(Integer id);

    Dish getDishByName(String name);

    void updateDish(Dish dish);

    void deleteDishById(Integer id);

}
