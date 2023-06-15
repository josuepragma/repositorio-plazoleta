package com.pragma.smallsquare.application.handler.category;

import com.pragma.smallsquare.application.dto.response.CategoryResponseDto;
import com.pragma.smallsquare.application.mapper.ICategoryResponseMapper;
import com.pragma.smallsquare.domain.api.ICategoryServicePort;
import com.pragma.smallsquare.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Override
    public List<CategoryResponseDto> getAllCategoriesDto() {
        List<Category> categoryList = categoryServicePort.getAllCategories();

        return categoryResponseMapper.toResponseDtoList(categoryList);
    }

    @Override
    public CategoryResponseDto getCategoryDtoById(Integer id) {
        Category category = categoryServicePort.getCategoryById(id);

        return categoryResponseMapper.toResponseDto(category);
    }

    @Override
    public CategoryResponseDto getCategoryDtoByName(String name) {
        Category category = categoryServicePort.getCategoryByName(name);

        return categoryResponseMapper.toResponseDto(category);
    }
}
