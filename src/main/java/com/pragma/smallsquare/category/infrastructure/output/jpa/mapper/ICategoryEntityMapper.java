package com.pragma.smallsquare.category.infrastructure.output.jpa.mapper;

import com.pragma.smallsquare.category.domain.model.Category;
import com.pragma.smallsquare.category.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {
    CategoryEntity toEntity(Category category);

    Category toCategory(CategoryEntity categoryEntity);

    List<Category> toCategoryList(List<CategoryEntity> categoryEntityList);
}
