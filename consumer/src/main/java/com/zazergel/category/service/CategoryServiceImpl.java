package com.zazergel.category.service;

import com.zazergel.category.dto.CategoryDto;
import com.zazergel.category.mapper.CategoryMapper;
import com.zazergel.category.model.Category;
import com.zazergel.category.repository.CategoryRepo;
import com.zazergel.exception.BadRequestException;
import com.zazergel.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAll(Pageable pageable) {
        log.info("Вывод списка всех категорий.");
        return categoryRepo
                .findAll(pageable)
                .stream()
                .map(categoryMapper::toCategoryDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        log.info("Вывод категории с id {}.", id);
        return categoryMapper.toCategoryDto(getCategoryById(id));
    }

    @Override
    @Transactional
    public CategoryDto add(CategoryDto categoryDto) {
        log.info("Создание категории {}.", categoryDto);
        checkCategoryExist(categoryDto.getName());
        return categoryMapper.toCategoryDto(categoryRepo.save(categoryMapper.toCategory(categoryDto)));
    }

    @Override
    @Transactional
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        log.info("Обновление категории {}.", categoryDto);
        Category repoCategory = getCategoryById(id);

        if (categoryDto.getName() != null) {
            checkCategoryExist(categoryDto.getName());
            repoCategory.setName(categoryDto.getName());
        }
        return categoryMapper.toCategoryDto(categoryRepo.save(repoCategory));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Удаление категории с id {}.", id);
        getCategoryById(id);
        categoryRepo.deleteById(id);
    }

    private Category getCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Категории с таким id не существует!"));
    }

    private void checkCategoryExist(String name) {
        Optional<Category> category = categoryRepo.findByName(name);
        if (category.isPresent()) {
            throw new BadRequestException("Такая категория уже существует!");
        }
    }

}
