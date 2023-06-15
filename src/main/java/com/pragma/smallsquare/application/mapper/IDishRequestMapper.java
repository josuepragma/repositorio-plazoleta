package com.pragma.smallsquare.restaurant.application.mapper;

import com.pragma.smallsquare.restaurant.application.dto.request.DishRequestDto;
import com.pragma.smallsquare.restaurant.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {

    Dish toDish(DishRequestDto dishRequestDto);

}
