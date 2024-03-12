package com.zazergel.category.service;

import com.zazergel.category.dto.CategoryDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAll(Pageable pageable);

    CategoryDto getById(Long id);

    CategoryDto add(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    void delete(Long id);

}
