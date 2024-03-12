package com.zazergel.category;


import com.zazergel.category.dto.CategoryDto;
import com.zazergel.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getAll(@RequestParam Integer from,
                                    @RequestParam Integer size) {
        return categoryService.getAll(PageRequest.of(from / size, size));
    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public CategoryDto add(@RequestBody CategoryDto categoryDto) {
        return categoryService.add(categoryDto);
    }

    @PutMapping("/{id}")
    public CategoryDto update(@PathVariable Long id,
                              @RequestBody CategoryDto categoryDto) {
        return categoryService.update(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }

}
