package com.pragma.smallsquare.restaurant.application.mapper;

import com.pragma.smallsquare.restaurant.application.dto.response.RestaurantSortResponseDto;
import com.pragma.smallsquare.restaurant.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantSortResponseMapper {

    RestaurantSortResponseDto toResponse(Restaurant restaurant);

    List<RestaurantSortResponseDto> toResponseList(List<Restaurant> restaurantList);
}
