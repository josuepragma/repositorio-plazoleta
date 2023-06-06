package com.pragma.smallsquare.dish.application.handler;

import com.pragma.smallsquare.dish.application.dto.DishRequestDto;
import com.pragma.smallsquare.dish.application.dto.DishRequestDto2Update;
import com.pragma.smallsquare.dish.application.dto.DishResponseDto;

import java.util.List;

public interface IDishHandler {
    void saveDishDto(DishRequestDto dishRequestDto);

    List<DishResponseDto> getAllDishesDto();

    DishResponseDto getDishDtoById(Integer id);

    DishResponseDto getDishDtoByName(String name);

    void updateDishDto(DishRequestDto2Update dishRequestDto2Update, Integer id);

    void deleteDishDtoById(Integer id);

}
