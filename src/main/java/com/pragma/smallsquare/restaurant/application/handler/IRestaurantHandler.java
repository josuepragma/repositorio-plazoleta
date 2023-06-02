package com.pragma.smallsquare.restaurant.application.handler;

import com.pragma.smallsquare.restaurant.application.dto.RestaurantRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantHandler {

    void saveRestaurantDto(RestaurantRequestDto restaurantRequestDto);

    List<RestaurantResponseDto> getAllRestaurantsDto();

    RestaurantResponseDto getRestaurantDtoByNit(String nit);

    RestaurantResponseDto getRestaurantDtoByName(String name);

    void updateRestaurantDto(RestaurantRequestDto restaurantRequestDto);

    void deleteRestaurantDtoByNit(String nit);
}
