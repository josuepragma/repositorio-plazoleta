package com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.adapter;

import com.pragma.smallsquare.restaurant.domain.model.Dish;
import com.pragma.smallsquare.restaurant.domain.spi.IDishPersistencePort;
import com.pragma.smallsquare.restaurant.insfrastructure.exceptions.*;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.entity.CategoryEntity;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.entity.DishEntity;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.mapper.IDishEntityMapper;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.repository.ICategoryRepository;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.repository.IDishRepository;
import com.pragma.smallsquare.restaurant.insfrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    private final IRestaurantRepository restaurantRepository;
    private final ICategoryRepository categoryRepository;

    @Override
    public void saveDish(Dish dish) {
        if (dishRepository.findByName(dish.getName()).isPresent()) {
            throw new DishAlreadyExistsException("Restaurant already exist with dish name:" + dish.getName());
        }

        DishEntity dishEntity = dishEntityMapper.toEntity(dish);
        dishRepository.save(dishEntity);
    }

    @Override
    public List<Dish> getAllDishes() {
        List<DishEntity> dishEntityList = dishRepository.findAll();

        if (dishEntityList.isEmpty()) {
            throw new NoDataFoundException("Dish List is empty");
        }

        return dishEntityMapper.toDishList(dishEntityList);
    }

    @Override
    public List<Dish> getAllDishesByIdRestaurantAndIdCategory(Integer idRestaurant,
                                                              Integer idCategory,
                                                              int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        RestaurantEntity restaurant = restaurantRepository.findById(idRestaurant)
                .orElseThrow(() -> new RestaurantNotFoundException("Id Restaurant Not Found"));

        CategoryEntity category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new CategoryNotFoundException("Id Category Not Found"));

        List<DishEntity> dishEntityList = dishRepository
                .findAllByRestaurantAndCategory(restaurant, category, pageable);

        if (dishEntityList.isEmpty()) {
            throw new NoDataFoundException("There is no DISHES" +
                    " in Restaurant: " + restaurant.getName() +
                    " with category: " + category.getName() +
                    " in page: " + page);
        }

        return dishEntityMapper.toDishList(dishEntityList);
    }

    @Override
    public Dish getDishById(Integer id) {
        DishEntity dishEntity = dishRepository.findById(id)
                .orElseThrow(() -> new DishNotFoundException("Dish searched doesn't exist with ID = " + id));

        return dishEntityMapper.toDish(dishEntity);
    }

    @Override
    public Dish getDishByName(String name) {
        DishEntity dishEntity = dishRepository.findByName(name)
                .orElseThrow(() -> new DishNotFoundException("Dish searched doesn't exist with NAME = " + name));

        return dishEntityMapper.toDish(dishEntity);
    }

    @Override
    public void updateDish(Dish dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public void deleteDishById(Integer id) {
        dishRepository.deleteById(id);
    }
}
