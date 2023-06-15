package com.pragma.smallsquare.restaurant.application.mapper;

import com.pragma.smallsquare.restaurant.application.dto.request.DishModifyRequestDto;
import com.pragma.smallsquare.restaurant.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper2Update {
    Dish toDish(DishModifyRequestDto dishModifyRequestDto);
}
