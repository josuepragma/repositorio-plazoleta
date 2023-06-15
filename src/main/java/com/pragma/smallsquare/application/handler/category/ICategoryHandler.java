package com.pragma.smallsquare.restaurant.application.handler.category;

import com.pragma.smallsquare.restaurant.application.dto.response.CategoryResponseDto;

import java.util.List;

public interface ICategoryHandler {

    List<CategoryResponseDto> getAllCategoriesDto();

    CategoryResponseDto getCategoryDtoById(Integer id);

    CategoryResponseDto getCategoryDtoByName(String name);

}
