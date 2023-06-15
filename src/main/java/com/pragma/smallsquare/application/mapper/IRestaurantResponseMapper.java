package com.pragma.smallsquare.application.mapper;

import com.pragma.smallsquare.application.dto.response.RestaurantResponseDto;
import com.pragma.smallsquare.domain.model.Restaurant;
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
