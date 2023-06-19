package com.pragma.smallsquare.application.mapper;

import com.pragma.smallsquare.application.dto.response.RestaurantResponse;
import com.pragma.smallsquare.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {

    RestaurantResponse toResponseDto(Restaurant restaurant);

    List<RestaurantResponse> toResponseDtoList(List<Restaurant> restaurantList);
}
