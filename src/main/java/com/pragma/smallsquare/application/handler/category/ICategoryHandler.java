package com.pragma.smallsquare.application.handler.category;

import com.pragma.smallsquare.application.dto.response.CategoryResponse;

import java.util.List;

public interface ICategoryHandler {

    List<CategoryResponse> getAllCategoriesDto();

    CategoryResponse getCategoryDtoById(Integer id);

    CategoryResponse getCategoryDtoByName(String name);

}
