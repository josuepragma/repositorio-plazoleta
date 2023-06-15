package com.pragma.smallsquare.domain.usecase;

import com.pragma.smallsquare.domain.api.ICategoryServicePort;
import com.pragma.smallsquare.domain.model.Category;
import com.pragma.smallsquare.domain.spi.ICategoryPersistencePort;

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
