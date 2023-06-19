package com.pragma.smallsquare.application.mapper;

import com.pragma.smallsquare.application.dto.response.RestaurantSortResponse;
import com.pragma.smallsquare.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantSortResponseMapper {

    RestaurantSortResponse toResponse(Restaurant restaurant);

    List<RestaurantSortResponse> toResponseList(List<Restaurant> restaurantList);
}
