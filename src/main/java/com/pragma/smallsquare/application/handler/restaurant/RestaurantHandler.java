package com.pragma.smallsquare.restaurant.application.handler.restaurant;

import com.pragma.smallsquare.restaurant.application.dto.request.RestaurantRequestDto;
import com.pragma.smallsquare.restaurant.application.dto.response.RestaurantResponseDto;
import com.pragma.smallsquare.restaurant.application.dto.response.RestaurantSortResponseDto;
import com.pragma.smallsquare.restaurant.application.dto.response.UserResponseDto;
import com.pragma.smallsquare.restaurant.application.handler.restaurant.IRestaurantHandler;
import com.pragma.smallsquare.restaurant.application.mapper.IRestaurantRequestMapper;
import com.pragma.smallsquare.restaurant.application.mapper.IRestaurantResponseMapper;
import com.pragma.smallsquare.restaurant.application.mapper.IRestaurantSortResponseMapper;
import com.pragma.smallsquare.restaurant.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.restaurant.domain.model.Restaurant;
import com.pragma.smallsquare.restaurant.insfrastructure.exceptions.UserIsNoOwnerException;
import com.pragma.smallsquare.restaurant.insfrastructure.feign.client.IUserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;
    private final IRestaurantSortResponseMapper restaurantSortResponseMapper;

    private final IUserFeignClient userFeignClient;

    @Override
    public void saveRestaurantDto(RestaurantRequestDto restaurantRequestDto) {
        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequestDto);

        UserResponseDto user = userFeignClient.getUserById(restaurantRequestDto.getIdOwner());
        if(user.getIdRole() != 2) {
            throw new UserIsNoOwnerException("IdOwner is not an OWNER user");
        }

        restaurantServicePort.saveRestaurant(restaurant);
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurantsDto() {
        return restaurantResponseMapper.toResponseDtoList(restaurantServicePort.getAllRestaurants());
    }

    @Override
    public List<RestaurantSortResponseDto> getAllRestaurantsDtoOrderByName(
            int page, int size) {
        List<Restaurant> restaurants = restaurantServicePort.getAllRestaurantsOrderByName(page, size);

        return restaurantSortResponseMapper.toResponseList(restaurants);
    }

    @Override
    public RestaurantResponseDto getRestaurantDtoById(Integer id) {
        Restaurant restaurant = restaurantServicePort.getRestaurantById(id);

        return restaurantResponseMapper.toResponseDto(restaurant);
    }

    @Override
    public RestaurantResponseDto getRestaurantDtoByNit(String nit) {
        Restaurant restaurant = restaurantServicePort.getRestaurantByNit(nit);

        return restaurantResponseMapper.toResponseDto(restaurant);
    }

    @Override
    public void updateRestaurantDto(RestaurantRequestDto restaurantRequestDto, Integer id) {
        Restaurant modifiedRestaurant = restaurantServicePort.getRestaurantById(id);
        modifiedRestaurant.setName(restaurantRequestDto.getName());
        modifiedRestaurant.setNit(restaurantRequestDto.getNit());
        modifiedRestaurant.setAddress(restaurantRequestDto.getAddress());
        modifiedRestaurant.setPhone(restaurantRequestDto.getPhone());
        modifiedRestaurant.setUrlLogo(restaurantRequestDto.getUrlLogo());
        modifiedRestaurant.setIdOwner(restaurantRequestDto.getIdOwner());

        restaurantServicePort.updateRestaurant(modifiedRestaurant);
    }

    @Override
    public void deleteRestaurantDtoById(Integer id) {
        restaurantServicePort.deleteRestaurantById(id);
    }
}
