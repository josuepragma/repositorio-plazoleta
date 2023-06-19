package com.pragma.smallsquare.application.handler.restaurant;

import com.pragma.smallsquare.application.dto.request.RestaurantRequest;
import com.pragma.smallsquare.application.dto.response.RestaurantResponse;
import com.pragma.smallsquare.application.dto.response.RestaurantSortResponse;

import java.util.List;

public interface IRestaurantHandler {

    void saveRestaurantDto(RestaurantRequest restaurantRequest);

    List<RestaurantSortResponse> getAllRestaurantsDtoOrderByName(int page, int size);

    RestaurantResponse getRestaurantDtoById(Integer id);

}
