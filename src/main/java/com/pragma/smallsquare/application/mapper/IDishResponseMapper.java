package com.pragma.smallsquare.application.mapper;

import com.pragma.smallsquare.application.dto.response.DishResponse;
import com.pragma.smallsquare.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {

    @Mapping(target = "restaurant", source = "dish.restaurant.name")
    @Mapping(target = "category", source = "dish.category.name")
    DishResponse toResponseDto(Dish dish);

    @Mapping(target = "restaurant", source = "dish.restaurant.name")
    @Mapping(target = "category", source = "dish.category.name")
    List<DishResponse> toResponseDtoList(List<Dish> dishList);

}
