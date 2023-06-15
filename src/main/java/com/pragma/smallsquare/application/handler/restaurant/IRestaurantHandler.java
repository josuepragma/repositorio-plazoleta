package com.pragma.smallsquare.application.handler.restaurant;

import com.pragma.smallsquare.application.dto.request.RestaurantRequestDto;
import com.pragma.smallsquare.application.dto.response.RestaurantResponseDto;
import com.pragma.smallsquare.application.dto.response.RestaurantSortResponseDto;

import java.util.List;

public interface IRestaurantHandler {

    void saveRestaurantDto(RestaurantRequestDto restaurantRequestDto);

    List<RestaurantSortResponseDto> getAllRestaurantsDtoOrderByName(int page, int size);

    RestaurantResponseDto getRestaurantDtoById(Integer id);

}
