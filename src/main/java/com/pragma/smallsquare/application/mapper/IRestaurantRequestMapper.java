package com.pragma.smallsquare.application.mapper;

import com.pragma.smallsquare.application.dto.request.RestaurantRequestDto;
import com.pragma.smallsquare.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantRequestMapper {
    Restaurant toRestaurant(RestaurantRequestDto restaurantRequestDto);
}
