package com.pragma.smallsquare.application.mapper;

import com.pragma.smallsquare.application.dto.response.CategoryResponseDto;
import com.pragma.smallsquare.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseMapper {
    CategoryResponseDto toResponseDto(Category category);

    List<CategoryResponseDto> toResponseDtoList(List<Category> categoryList);
}
