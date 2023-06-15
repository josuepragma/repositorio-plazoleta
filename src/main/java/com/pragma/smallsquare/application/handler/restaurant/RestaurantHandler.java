package com.pragma.smallsquare.application.handler.restaurant;

import com.pragma.smallsquare.application.dto.request.RestaurantRequestDto;
import com.pragma.smallsquare.application.dto.response.RestaurantResponseDto;
import com.pragma.smallsquare.application.dto.response.RestaurantSortResponseDto;
import com.pragma.smallsquare.application.dto.response.UserResponseDto;
import com.pragma.smallsquare.application.mapper.IRestaurantRequestMapper;
import com.pragma.smallsquare.application.mapper.IRestaurantResponseMapper;
import com.pragma.smallsquare.application.mapper.IRestaurantSortResponseMapper;
import com.pragma.smallsquare.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.insfrastructure.exceptions.UserIsNoOwnerException;
import com.pragma.smallsquare.insfrastructure.feign.client.IUserFeignClient;
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
}
