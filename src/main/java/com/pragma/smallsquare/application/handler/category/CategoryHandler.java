package com.pragma.smallsquare.application.handler.category;

import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.pragma.smallsquare.application.dto.response.CategoryResponse;
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
@XRayEnabled
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Override
    public List<CategoryResponse> getAllCategoriesDto() {
        List<Category> categoryList = categoryServicePort.getAllCategories();

        return categoryResponseMapper.toResponseDtoList(categoryList);
    }

    @Override
    public CategoryResponse getCategoryDtoById(Integer id) {
        Category category = categoryServicePort.getCategoryById(id);

        return categoryResponseMapper.toResponseDto(category);
    }

    @Override
    public CategoryResponse getCategoryDtoByName(String name) {
        Category category = categoryServicePort.getCategoryByName(name);

        return categoryResponseMapper.toResponseDto(category);
    }
}
