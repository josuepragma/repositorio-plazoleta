package com.pragma.smallsquare.application.handler.dish;

import com.pragma.smallsquare.application.dto.request.DishDisableEnableRequest;
import com.pragma.smallsquare.application.dto.request.DishModifyRequest;
import com.pragma.smallsquare.application.dto.request.DishRequest;
import com.pragma.smallsquare.application.dto.response.DishResponse;

import java.util.List;

public interface IDishHandler {
    void saveDishDto(DishRequest dishRequest, Integer currentUserId);

    List<DishResponse> getAllDishesByIdRestaurantAndIdCategory(Integer idRestaurant, Integer idCategory, int page, int size);

    DishResponse getDishDtoById(Integer id);

    void updatePriceAndDescriptionDishDto(DishModifyRequest dishRequest, Integer dishId, Integer currentUserId);

    void changeStatusDishDto(DishDisableEnableRequest dishRequest, Integer dishId, Integer currentUserId);

}
