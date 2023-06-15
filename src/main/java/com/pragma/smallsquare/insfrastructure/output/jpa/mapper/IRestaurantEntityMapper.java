package com.pragma.smallsquare.insfrastructure.output.jpa.mapper;

import com.pragma.smallsquare.domain.model.Restaurant;
import com.pragma.smallsquare.insfrastructure.output.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEntityMapper {

    RestaurantEntity toEntity(Restaurant restaurant);

    Restaurant toRestaurant(RestaurantEntity restaurantEntity);

    List<Restaurant> toRestaurantList(List<RestaurantEntity> restaurantEntityList);
}
