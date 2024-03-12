package com.zazergel.category;

import com.zazergel.category.model.CategoryDto;
import com.zazergel.configuration.Config;
import com.zazergel.configuration.Create;
import com.zazergel.configuration.Update;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@Validated
@Slf4j
public class CategoryController {

    private final CategoryClient categoryClient;

    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(defaultValue = Config.PAGE_DEFAULT_FROM) @PositiveOrZero Integer from,
                                         @RequestParam(defaultValue = Config.PAGE_DEFAULT_SIZE) @Positive Integer size) {
        return categoryClient.getAll(from, size);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return categoryClient.getById(id);
    }

    @PostMapping
    public ResponseEntity<Object> add(@Validated(Create.class) @RequestBody CategoryDto categoryDto) {
        return categoryClient.add(categoryDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,
                                         @Validated(Update.class) @RequestBody CategoryDto categoryDto) {
        return categoryClient.update(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryClient.delete(id);
    }
}
