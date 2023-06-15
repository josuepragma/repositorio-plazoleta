package com.pragma.smallsquare.restaurant.domain.usecase;

import com.pragma.smallsquare.restaurant.domain.api.IDishServicePort;
import com.pragma.smallsquare.restaurant.domain.model.Dish;
import com.pragma.smallsquare.restaurant.domain.spi.IDishPersistencePort;

import java.util.List;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public void saveDish(Dish dish) {
        dishPersistencePort.saveDish(dish);
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }

    @Override
    public List<Dish> getAllDishesByIdRestaurantAndIdCategory(Integer idRestaurant, Integer idCategory, int page, int size) {
        return dishPersistencePort
                .getAllDishesByIdRestaurantAndIdCategory(idRestaurant, idCategory, page, size);
    }

    @Override
    public Dish getDishById(Integer id) {
        return dishPersistencePort.getDishById(id);
    }

    @Override
    public Dish getDishByName(String name) {
        return dishPersistencePort.getDishByName(name);
    }

    @Override
    public void updateDish(Dish dish) {
        dishPersistencePort.updateDish(dish);
    }

    @Override
    public void deleteDishById(Integer id) {
        dishPersistencePort.deleteDishById(id);
    }
}
