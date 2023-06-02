package com.pragma.smallsquare.restaurant.application.mapper;

import com.pragma.smallsquare.restaurant.application.dto.RestaurantResponseDto;
import com.pragma.smallsquare.restaurant.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {

    RestaurantResponseDto toResponseDto(Restaurant restaurant);

    List<RestaurantResponseDto> toResponseDtoList(List<Restaurant> restaurantList);
}
