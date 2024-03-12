package com.zazergel.product.service;

import com.zazergel.comment.dto.CommentDto;
import com.zazergel.comment.dto.CommentRequestDto;
import com.zazergel.product.dto.ProductDto;
import com.zazergel.product.dto.ProductExtendedDto;
import com.zazergel.product.enums.ProductSortType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ProductExtendedDto> getAll(Pageable pageable, ProductSortType sort);

    ProductExtendedDto getById(Long id);

    ProductDto add(ProductDto productDto);

    ProductDto update(Long id, ProductDto productDto);

    void delete(Long id);

    Page<ProductExtendedDto> search(String text, Long price, String category, ProductSortType sort,
                                    Integer from, Integer size);

    CommentDto addComment(Long id, CommentRequestDto commentRequestDto);
}
