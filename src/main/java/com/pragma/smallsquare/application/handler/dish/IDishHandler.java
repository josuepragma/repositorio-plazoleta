package com.pragma.smallsquare.application.handler.dish;

import com.pragma.smallsquare.application.dto.request.DishDisableEnableRequestDto;
import com.pragma.smallsquare.application.dto.request.DishRequestDto;
import com.pragma.smallsquare.application.dto.request.DishModifyRequestDto;
import com.pragma.smallsquare.application.dto.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {
    void saveDishDto(DishRequestDto dishRequestDto, Integer currentUserId);

    List<DishResponseDto> getAllDishesByIdRestaurantAndIdCategory(Integer idRestaurant, Integer idCategory, int page, int size);

    DishResponseDto getDishDtoById(Integer id);

    void updatePriceAndDescriptionDishDto(DishModifyRequestDto dishRequest, Integer dishId, Integer currentUserId);

    void changeStatusDishDto(DishDisableEnableRequestDto dishRequest, Integer dishId, Integer currentUserId);

}
