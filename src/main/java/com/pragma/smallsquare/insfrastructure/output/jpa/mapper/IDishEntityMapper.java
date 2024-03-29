package com.pragma.smallsquare.insfrastructure.output.jpa.mapper;

import com.pragma.smallsquare.domain.model.Dish;
import com.pragma.smallsquare.insfrastructure.output.jpa.entity.DishEntity;
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
