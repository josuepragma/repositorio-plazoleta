package com.pragma.smallsquare.restaurant.domain.spi;

import com.pragma.smallsquare.restaurant.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {

    List<Category> getAllCategories();

    Category getCategoryById(Integer id);

    Category getCategoryByName(String name);
}
