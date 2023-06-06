package com.pragma.smallsquare.dish.infrastructure.output.jpa.mapper;

import com.pragma.smallsquare.dish.domain.model.Dish;
import com.pragma.smallsquare.dish.infrastructure.output.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishEntityMapper {

    DishEntity toEntity(Dish dish);

    Dish toDish(DishEntity dishEntity);

    List<Dish> toDishList(List<DishEntity> dishEntityList);

}
