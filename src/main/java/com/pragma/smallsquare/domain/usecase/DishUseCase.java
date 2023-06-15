package com.pragma.smallsquare.domain.usecase;

import com.pragma.smallsquare.domain.api.IDishServicePort;
import com.pragma.smallsquare.domain.spi.IDishPersistencePort;
import com.pragma.smallsquare.domain.model.Dish;

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
    public List<Dish> getAllDishesByIdRestaurantAndIdCategory(Integer idRestaurant, Integer idCategory, int page, int size) {
        return dishPersistencePort
                .getAllDishesByIdRestaurantAndIdCategory(idRestaurant, idCategory, page, size);
    }

    @Override
    public Dish getDishById(Integer id) {
        return dishPersistencePort.getDishById(id);
    }

    @Override
    public void updateDish(Dish dish) {
        dishPersistencePort.updateDish(dish);
    }

}
