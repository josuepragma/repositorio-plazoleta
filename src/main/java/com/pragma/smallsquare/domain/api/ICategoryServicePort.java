package com.pragma.smallsquare.domain.api;

import com.pragma.smallsquare.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {

    List<Category> getAllCategories();

    Category getCategoryById(Integer id);

    Category getCategoryByName(String name);
}
