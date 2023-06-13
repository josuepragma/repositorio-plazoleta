package com.pragma.smallsquare.restaurant.application.handler.restaurant;

import com.pragma.smallsquare.restaurant.application.dto.request.RestaurantRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.response.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantHandler {

    void saveRestaurantDto(RestaurantRequestDto restaurantRequestDto);

    List<RestaurantResponseDto> getAllRestaurantsDto();

    RestaurantResponseDto getRestaurantDtoById(Integer id);

    RestaurantResponseDto getRestaurantDtoByNit(String nit);

    void updateRestaurantDto(RestaurantRequestDto restaurantRequestDto, Integer id);

    void deleteRestaurantDtoById(Integer id);

}
