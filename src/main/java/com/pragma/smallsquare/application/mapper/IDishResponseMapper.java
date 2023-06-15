package com.pragma.smallsquare.restaurant.application.mapper;

import com.pragma.smallsquare.restaurant.domain.model.Category;
import com.pragma.smallsquare.restaurant.application.dto.response.DishResponseDto;
import com.pragma.smallsquare.restaurant.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {

    @Mapping(target = "restaurant", source = "dish.restaurant.name")
    @Mapping(target = "category", source = "dish.category.name")
    DishResponseDto toResponseDto(Dish dish);

    @Mapping(target = "restaurant", source = "dish.restaurant.name")
    @Mapping(target = "category", source = "dish.category.name")
    List<DishResponseDto> toResponseDtoList(List<Dish> dishList);

//    default DishResponseDto toResponseDto(Dish dish, Category category) {
//        DishResponseDto dishResponseDto = new DishResponseDto();
//        dishResponseDto.setId(dish.getId());
//        dishResponseDto.setName(dish.getName());
//        dishResponseDto.setPrice(dish.getPrice());
//        dishResponseDto.setDescription(dish.getDescription());
//        dishResponseDto.setUrlImage(dish.getUrlImage());
//        dishResponseDto.setActive(dish.getActive());
//        dishResponseDto.setIdRestaurant(dish.getIdRestaurant());
//
//        dishResponseDto.setCategory(category.getName());
//
//        return dishResponseDto;
//    }
//
//    default List<DishResponseDto> toResponseDtoList(List<Dish> dishList, List<Category> categoryList) {
//        return dishList.stream()
//                .map(dishParam -> {
//                    DishResponseDto dishResponseDto = new DishResponseDto();
//                    dishResponseDto.setId(dishParam.getId());
//                    dishResponseDto.setName(dishParam.getName());
//                    dishResponseDto.setPrice(dishParam.getPrice());
//                    dishResponseDto.setDescription(dishParam.getDescription());
//                    dishResponseDto.setUrlImage(dishParam.getUrlImage());
//                    dishResponseDto.setActive(dishParam.getActive());
//                    dishResponseDto.setIdRestaurant(dishParam.getIdRestaurant());
//                    dishResponseDto.setCategory(categoryList.stream().filter(categoryParam -> categoryParam.getId().equals(dishParam.getIdCategory())).findFirst().orElse(null).getName());
//
//                    return dishResponseDto;
//                }).collect(Collectors.toList());
//    }
}
