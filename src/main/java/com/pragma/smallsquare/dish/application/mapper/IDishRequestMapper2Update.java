package com.pragma.smallsquare.dish.application.mapper;

import com.pragma.smallsquare.dish.application.dto.DishRequestDto2Update;
import com.pragma.smallsquare.dish.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper2Update {
    Dish toDish(DishRequestDto2Update dishRequestDto2Update);
}
