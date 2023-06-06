package com.pragma.smallsquare.dish.domain.usecase;

import com.pragma.smallsquare.dish.domain.api.IDishServicePort;
import com.pragma.smallsquare.dish.domain.model.Dish;
import com.pragma.smallsquare.dish.domain.spi.IDishPersistencePort;

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
