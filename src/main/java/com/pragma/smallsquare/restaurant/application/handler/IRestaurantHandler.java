package com.pragma.smallsquare.restaurant.application.handler;

import com.pragma.smallsquare.restaurant.application.dto.RestaurantRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.RestaurantResponseDto;
import com.pragma.smallsquare.restaurant.application.dto.UserResponseDto;

import java.util.List;

public interface IRestaurantHandler {

    UserResponseDto getOwnerUser(Integer id);

    void saveRestaurantDto(RestaurantRequestDto restaurantRequestDto);

    List<RestaurantResponseDto> getAllRestaurantsDto();

    RestaurantResponseDto getRestaurantDtoById(Integer id);

    RestaurantResponseDto getRestaurantDtoByNit(String nit);

    void updateRestaurantDto(RestaurantRequestDto restaurantRequestDto, Integer id);

    void deleteRestaurantDtoById(Integer id);

}
