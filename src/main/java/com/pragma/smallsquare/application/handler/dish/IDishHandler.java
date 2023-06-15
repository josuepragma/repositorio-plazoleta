package com.pragma.smallsquare.restaurant.application.handler.dish;

import com.pragma.smallsquare.restaurant.application.dto.request.DishDisableEnableRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.request.DishModifyRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.request.DishRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.response.DishResponseDto;
import com.pragma.smallsquare.restaurant.domain.model.Dish;

import java.util.List;

public interface IDishHandler {
    void saveDishDto(DishRequestDto dishRequestDto, Integer currentUserId);

    List<DishResponseDto> getAllDishesDto();

    List<DishResponseDto> getAllDishesByIdRestaurantAndIdCategory(Integer idRestaurant, Integer idCategory, int page, int size);

    DishResponseDto getDishDtoById(Integer id);

    DishResponseDto getDishDtoByName(String name);

    void updatePriceAndDescriptionDishDto(DishModifyRequestDto dishRequest, Integer dishId, Integer currentUserId);

    void changeStatusDishDto(DishDisableEnableRequestDto dishRequest, Integer dishId, Integer currentUserId);

    void deleteDishDtoById(Integer id);

}
