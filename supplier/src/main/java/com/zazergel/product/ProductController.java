package com.zazergel.product;

import com.zazergel.comment.CommentRequestDto;
import com.zazergel.configuration.Config;
import com.zazergel.configuration.Create;
import com.zazergel.configuration.Update;
import com.zazergel.product.enums.ProductSortType;
import com.zazergel.product.model.ProductDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ProductController {
    private final ProductClient productClient;


    @GetMapping
    public ResponseEntity<Object> getAll(@RequestParam(defaultValue = Config.PAGE_DEFAULT_FROM) @PositiveOrZero Integer from,
                                         @RequestParam(defaultValue = Config.PAGE_DEFAULT_SIZE) @Positive Integer size,
                                         @RequestParam(defaultValue = "DEFAULT") ProductSortType sort) {
        return productClient.getAll(from, size, sort);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        return productClient.getById(id);
    }

    @PostMapping
    public ResponseEntity<Object> add(@Validated(Create.class) @RequestBody ProductDto productDto) {
        return productClient.add(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id,
                                         @Validated(Update.class) @RequestBody ProductDto productDto) {
        return productClient.update(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productClient.delete(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> search(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "DEFAULT") ProductSortType sort,
            @RequestParam(defaultValue = Config.PAGE_DEFAULT_FROM) @PositiveOrZero Integer from,
            @RequestParam(defaultValue = Config.PAGE_DEFAULT_SIZE) @Positive Integer size) {
        return productClient.search(text, maxPrice, category, sort, from, size);
    }

    @PostMapping("{id}/comment")
    public ResponseEntity<Object> addComment(@PathVariable Long id,
                                             @Valid @RequestBody CommentRequestDto commentRequestDto) {
        return productClient.addComment(id, commentRequestDto);
    }
}

