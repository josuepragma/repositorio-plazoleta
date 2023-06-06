package com.pragma.smallsquare.category.domain.spi;

import com.pragma.smallsquare.category.domain.model.Category;

import java.util.List;

public interface ICategoryPersistencePort {

    List<Category> getAllCategories();

    Category getCategoryById(Integer id);

    Category getCategoryByName(String name);
}
