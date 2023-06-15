package com.pragma.smallsquare.domain.spi;

import com.pragma.smallsquare.domain.model.Dish;

import java.util.List;

public interface IDishPersistencePort {
    void saveDish(Dish dish);

    List<Dish> getAllDishesByIdRestaurantAndIdCategory(Integer idRestaurant, Integer idCategory, int page, int size);

    Dish getDishById(Integer id);

    void updateDish(Dish dish);

}
