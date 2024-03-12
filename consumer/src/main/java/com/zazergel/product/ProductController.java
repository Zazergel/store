package com.zazergel.product;

import com.zazergel.comment.dto.CommentDto;
import com.zazergel.comment.dto.CommentRequestDto;
import com.zazergel.product.dto.ProductDto;
import com.zazergel.product.dto.ProductExtendedDto;
import com.zazergel.product.enums.ProductSortType;
import com.zazergel.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductExtendedDto> getAll(@RequestParam Integer from,
                                           @RequestParam Integer size,
                                           @RequestParam ProductSortType sort) {
        return productService.getAll(PageRequest.of(from / size, size), sort);
    }

    @GetMapping("/{id}")
    public ProductExtendedDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public ProductDto add(@RequestBody ProductDto productDto) {
        return productService.add(productDto);
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable Long id,
                             @RequestBody ProductDto productDto) {
        return productService.update(id, productDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/search")
    public Page<ProductExtendedDto> search(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) ProductSortType sort,
            @RequestParam Integer from,
            @RequestParam Integer size) {
        return productService.search(text, maxPrice, category, sort, from, size);
    }

    @PostMapping("{id}/comment")
    public CommentDto addComment(@PathVariable long id,
                                 @RequestBody CommentRequestDto commentRequestDto) {
        return productService.addComment(id, commentRequestDto);
    }
}