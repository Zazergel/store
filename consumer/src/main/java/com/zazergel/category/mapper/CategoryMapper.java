package com.zazergel.category.mapper;

import com.zazergel.category.dto.CategoryDto;
import com.zazergel.category.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);

    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "name", expression = "java(categoryDto.getName())")
    Category toCategory(CategoryDto categoryDto);
}
