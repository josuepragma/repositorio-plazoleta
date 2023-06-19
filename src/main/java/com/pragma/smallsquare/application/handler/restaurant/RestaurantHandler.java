package com.pragma.smallsquare.application.handler.restaurant;

import com.pragma.smallsquare.application.dto.request.RestaurantRequest;
import com.pragma.smallsquare.application.dto.response.RestaurantResponse;
import com.pragma.smallsquare.application.dto.response.RestaurantSortResponse;
import com.pragma.smallsquare.application.dto.response.UserResponse;
import com.pragma.smallsquare.application.mapper.IRestaurantRequestMapper;
import com.pragma.smallsquare.application.mapper.IRestaurantResponseMapper;
import com.pragma.smallsquare.application.mapper.IRestaurantSortResponseMapper;
import com.pragma.smallsquare.domain.api.IRestaurantServicePort;
import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.application.exceptions.UserIsNoOwnerException;
import com.pragma.smallsquare.insfrastructure.output.feign.IUserFeignClient;
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
    public void saveRestaurantDto(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRequestMapper.toRestaurant(restaurantRequest);

        UserResponse user = userFeignClient.getUserById(restaurantRequest.getIdOwner());
        if(user.getIdRole() != 2) {
            throw new UserIsNoOwnerException("IdOwner is not an OWNER user");
        }

        restaurantServicePort.saveRestaurant(restaurant);
    }

    @Override
    public List<RestaurantSortResponse> getAllRestaurantsDtoOrderByName(
            int page, int size) {
        List<Restaurant> restaurants = restaurantServicePort.getAllRestaurantsOrderByName(page, size);

        return restaurantSortResponseMapper.toResponseList(restaurants);
    }

    @Override
    public RestaurantResponse getRestaurantDtoById(Integer id) {
        Restaurant restaurant = restaurantServicePort.getRestaurantById(id);

        return restaurantResponseMapper.toResponseDto(restaurant);
    }
}
