package com.pragma.smallsquare.category.domain.api;

import com.pragma.smallsquare.category.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {

    List<Category> getAllCategories();

    Category getCategoryById(Integer id);

    Category getCategoryByName(String name);
}
