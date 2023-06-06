package com.pragma.smallsquare.category.application.handler;

import com.pragma.smallsquare.category.application.dto.CategoryResponseDto;

import java.util.List;

public interface ICategoryHandler {

    List<CategoryResponseDto> getAllCategoriesDto();

    CategoryResponseDto getCategoryDtoById(Integer id);

    CategoryResponseDto getCategoryDtoByName(String name);

}
