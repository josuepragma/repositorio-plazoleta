package com.pragma.smallsquare.dish.application.handler;

import com.pragma.smallsquare.category.domain.api.ICategoryServicePort;
import com.pragma.smallsquare.category.domain.model.Category;
import com.pragma.smallsquare.dish.application.dto.DishRequestDto;
import com.pragma.smallsquare.dish.application.dto.DishRequestDto2Update;
import com.pragma.smallsquare.dish.application.dto.DishResponseDto;
import com.pragma.smallsquare.dish.application.mapper.IDishRequestMapper;
import com.pragma.smallsquare.dish.application.mapper.IDishResponseMapper;
import com.pragma.smallsquare.dish.domain.api.IDishServicePort;
import com.pragma.smallsquare.dish.domain.model.Dish;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DishHandler implements IDishHandler{

    private final IDishServicePort dishServicePort;
    private final ICategoryServicePort categoryServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public void saveDishDto(DishRequestDto dishRequestDto) {
        Dish dish = dishRequestMapper.toDish(dishRequestDto);
        Category category = categoryServicePort.getCategoryByName(dishRequestDto.getCategory());
        dish.setIdCategory(category.getId());
        dish.setActive(true);

        //  Modify at the future in order to do HU-05
        dish.setIdRestaurant(1);

        dishServicePort.saveDish(dish);
    }

    @Override
    public List<DishResponseDto> getAllDishesDto() {
        return dishResponseMapper.toResponseDtoList(dishServicePort.getAllDishes(), categoryServicePort.getAllCategories());
    }

    @Override
    public DishResponseDto getDishDtoById(Integer id) {
        Dish dish = dishServicePort.getDishById(id);
        Category category = categoryServicePort.getCategoryById(dish.getIdCategory());

        return dishResponseMapper.toResponseDto(dish, category);
    }

    @Override
    public DishResponseDto getDishDtoByName(String name) {
        Dish dish = dishServicePort.getDishByName(name);
        Category category = categoryServicePort.getCategoryById(dish.getIdCategory());

        return dishResponseMapper.toResponseDto(dish, category);
    }

    @Override
    public void updateDishDto(DishRequestDto2Update dishRequestDto2Update, Integer id) {
        Dish updatedDish = dishServicePort.getDishById(id);
        updatedDish.setDescription(dishRequestDto2Update.getDescription());
        updatedDish.setPrice(dishRequestDto2Update.getPrice());

        dishServicePort.updateDish(updatedDish);
    }

    @Override
    public void deleteDishDtoById(Integer id) {
        dishServicePort.deleteDishById(id);
    }
}
