package com.pragma.smallsquare.restaurant.domain.usecase;

import com.pragma.smallsquare.restaurant.domain.api.ICategoryServicePort;
import com.pragma.smallsquare.restaurant.domain.model.Category;
import com.pragma.smallsquare.restaurant.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryPersistencePort.getAllCategories();
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryPersistencePort.getCategoryById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryPersistencePort.getCategoryByName(name);
    }
}
