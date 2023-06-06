package com.pragma.smallsquare.category.infrastructure.output.jpa.adapter;

import com.pragma.smallsquare.category.domain.model.Category;
import com.pragma.smallsquare.category.domain.spi.ICategoryPersistencePort;
import com.pragma.smallsquare.category.infrastructure.exceptions.CategoryNotFoundException;
import com.pragma.smallsquare.category.infrastructure.output.jpa.entity.CategoryEntity;
import com.pragma.smallsquare.category.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.pragma.smallsquare.category.infrastructure.output.jpa.repository.ICategoryRepository;
import com.pragma.smallsquare.restaurant.insfrastructure.exceptions.NoDataFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();

        if (categoryEntityList.isEmpty()) {
            throw new NoDataFoundException("Category List is empty");
        }

        return categoryEntityMapper.toCategoryList(categoryEntityList);
    }

    @Override
    public Category getCategoryById(Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category searched doesn't exist with ID = " + id));

        return categoryEntityMapper.toCategory(categoryEntity);
    }

    @Override
    public Category getCategoryByName(String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException("Category searched doesn't exist with NAME = " + name));

        return categoryEntityMapper.toCategory(categoryEntity);
    }
}
