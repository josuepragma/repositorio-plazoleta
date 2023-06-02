package com.pragma.smallsquare.restaurant.application.handler;

import com.pragma.smallsquare.restaurant.application.dto.RestaurantRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.RestaurantResponseDto;
import com.pragma.smallsquare.restaurant.application.mapper.IRestaurantRequestMapper;
import com.pragma.smallsquare.restaurant.application.mapper.IRestaurantResponseMapper;
import com.pragma.smallsquare.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.restaurant.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler{

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    @Override
    public void saveRestaurantDto(RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDto);

        restaurantServicePort.saveRestaurant(restaurant);
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurantsDto() {
        return restaurantResponseMapper.toResponseDtoList(restaurantServicePort.getAllRestaurants());
    }

    @Override
    public RestaurantResponseDto getRestaurantDtoByNit(String nit) {
        Restaurant restaurant = restaurantServicePort.getRestaurantByNit(nit);

        return restaurantResponseMapper.toResponseDto(restaurant);
    }

    @Override
    public RestaurantResponseDto getRestaurantDtoByName(String name) {
        Restaurant restaurant = restaurantServicePort.getRestaurantByName(name);

        return restaurantResponseMapper.toResponseDto(restaurant);
    }

    @Override
    public void updateRestaurantDto(RestaurantRequestDto restaurantRequestDto) {
        Restaurant oldRestaurant = restaurantServicePort.getRestaurantByNit(restaurantRequestDto.getNit());

        Restaurant newRestaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
        newRestaurant.setId(oldRestaurant.getId());

        restaurantServicePort.updateRestaurant(newRestaurant);
    }

    @Override
    public void deleteRestaurantDtoByNit(String nit) {
        restaurantServicePort.deleteRestaurantByNit(nit);
    }
}
