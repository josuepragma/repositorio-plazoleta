package com.pragma.smallsquare.dish.application.mapper;

import com.pragma.smallsquare.category.domain.model.Category;
import com.pragma.smallsquare.dish.application.dto.DishResponseDto;
import com.pragma.smallsquare.dish.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {

    default DishResponseDto toResponseDto(Dish dish, Category category) {
        DishResponseDto dishResponseDto = new DishResponseDto();
        dishResponseDto.setName(dish.getName());
        dishResponseDto.setPrice(dish.getPrice());
        dishResponseDto.setDescription(dish.getDescription());
        dishResponseDto.setUrlImage(dish.getUrlImage());
        dishResponseDto.setActive(dish.getActive());
        dishResponseDto.setIdRestaurant(dish.getIdRestaurant());

        dishResponseDto.setCategory(category.getName());

        return dishResponseDto;
    }

    default List<DishResponseDto> toResponseDtoList(List<Dish> dishList, List<Category> categoryList) {
        return dishList.stream()
                .map(dishParam -> {
                    DishResponseDto dishResponseDto = new DishResponseDto();
                    dishResponseDto.setName(dishParam.getName());
                    dishResponseDto.setPrice(dishParam.getPrice());
                    dishResponseDto.setDescription(dishParam.getDescription());
                    dishResponseDto.setUrlImage(dishParam.getUrlImage());
                    dishResponseDto.setActive(dishParam.getActive());
                    dishResponseDto.setIdRestaurant(dishParam.getIdRestaurant());
                    dishResponseDto.setCategory(categoryList.stream().filter(categoryParam -> categoryParam.getId().equals(dishParam.getIdCategory())).findFirst().orElse(null).getName());

                    return dishResponseDto;
                }).collect(Collectors.toList());
    }

//    DishResponseDto toResponseDto(Dish dish);
//    List<DishResponseDto> toResponseDtoList(List<Dish> dishList);
}
