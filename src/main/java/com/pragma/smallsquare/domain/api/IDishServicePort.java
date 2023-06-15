package com.pragma.smallsquare.restaurant.domain.api;

import com.pragma.smallsquare.restaurant.domain.model.Dish;

import java.util.List;

public interface IDishServicePort {
    void saveDish(Dish dish);

    List<Dish> getAllDishes();

    List<Dish> getAllDishesByIdRestaurantAndIdCategory(Integer idRestaurant, Integer idCategory, int page, int size);

    Dish getDishById(Integer id);

    Dish getDishByName(String name);

    void updateDish(Dish dish);

    void deleteDishById(Integer id);

}
