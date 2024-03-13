package com.zazergel.product.service;

import com.zazergel.category.model.Category;
import com.zazergel.category.repository.CategoryRepo;
import com.zazergel.comment.dto.CommentDto;
import com.zazergel.comment.dto.CommentRequestDto;
import com.zazergel.comment.mapper.CommentMapper;
import com.zazergel.comment.model.Comment;
import com.zazergel.comment.repository.CommentRepo;
import com.zazergel.exception.NotFoundException;
import com.zazergel.product.dto.ProductDto;
import com.zazergel.product.dto.ProductExtendedDto;
import com.zazergel.product.enums.ProductSortType;
import com.zazergel.product.mapper.ProductMapper;
import com.zazergel.product.model.Product;
import com.zazergel.product.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final CommentRepo commentRepo;
    private final ProductMapper productMapper;
    private final CommentMapper commentMapper;

    @Override
    public Page<ProductExtendedDto> getAll(Pageable pageable, ProductSortType sort) {
        log.info("Вывод списка всех продуктов.");

        List<Product> products = productRepo.findAll();

        return getResult(products, sort, pageable);
    }


    @Override
    public ProductExtendedDto getById(Long id) {
        log.info("Вывод продукта с id {}.", id);

        Product product = getProductById(id);
        return productMapper
                .toProductExtendedDto(product);
    }


    @Override
    @Transactional
    public ProductDto add(ProductDto productDto) {
        log.info("Создание продукта {}.", productDto);

        Category category = getCategoryById(productDto.getCategoryId());
        Product product = productMapper.toProduct(productDto, category);
        return productMapper.toProductDto(productRepo.save(product));
    }

    @Override
    @Transactional
    public ProductDto update(Long id, ProductDto productDto) {
        log.info("Обновление продукта {}.", productDto);

        Product repoProduct = getProductById(id);

        if (productDto.getName() != null) {
            repoProduct.setName(productDto.getName());
        }
        if (productDto.getDescription() != null) {
            repoProduct.setDescription(productDto.getDescription());
        }
        if (productDto.getPrice() != null) {
            repoProduct.setPrice(productDto.getPrice());
        }
        if (productDto.getCategoryId() != null) {
            repoProduct.setCategory(getCategoryById(productDto.getCategoryId()));
        }

        return productMapper.toProductDto(productRepo.save(repoProduct));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Удаление продукта с id {}.", id);
        productRepo.deleteById(id);

    }

    @Override
    public Page<ProductExtendedDto> search(String text,
                                           Long maxPrice,
                                           String category,
                                           ProductSortType sort,
                                           Integer from, Integer size) {
        log.info("Поиск продукта по параметрам: {}, {}, {} и типом сортировки {}", text, maxPrice, category, sort);

        if ((text == null || text.isBlank()) && maxPrice == null && category == null && sort == null) {
            return getAll(PageRequest.of(from / size, size), ProductSortType.DEFAULT);
        }

        List<Product> foundedProducts = productRepo.searchProducts(text, maxPrice, category);

        return getResult(foundedProducts, sort, PageRequest.of(from / size, size));
    }

    @Override
    @Transactional
    public CommentDto addComment(Long id, CommentRequestDto commentRequestDto) {
        log.info("Добавление комментария продукту с id {}.", id);

        Product product = getProductById(id);

        Comment comment = commentMapper.commentRequestDtoToComment(commentRequestDto,
                LocalDateTime.now(), product);

        return commentMapper.commentToCommentDto(commentRepo.save(comment));
    }

    private Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Продукта с таким id не существует!"));
    }

    private Category getCategoryById(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Категории с таким id не существует!"));
    }

    private Page<ProductExtendedDto> getResult(List<Product> products,
                                               ProductSortType sort, Pageable pageable) {
        if (products.isEmpty()) {
            return Page.empty();
        }

        List<ProductExtendedDto> productExtendedDtos = new ArrayList<>(products.stream()
                .map(productMapper::toProductExtendedDto)
                .toList());

        sort(productExtendedDtos, sort);

        return new PageImpl<>(productExtendedDtos, pageable, productExtendedDtos.size());
    }


    private void sort(List<ProductExtendedDto> productExtendedDtos, ProductSortType sort) {

        if (needSort(sort, ProductSortType.NAME)) {
            productExtendedDtos.sort(Comparator.comparing(ProductExtendedDto::getName));
        } else if (needSort(sort, ProductSortType.CATEGORY)) {
            productExtendedDtos.sort(Comparator.comparing(ProductExtendedDto::getCategory));
        } else if (needSort(sort, ProductSortType.PRICE)) {
            productExtendedDtos.sort(Comparator.comparing(ProductExtendedDto::getPrice));
        } else if (needSort(sort, ProductSortType.DEFAULT) || sort == null) {
            productExtendedDtos.sort(Comparator.comparing(ProductExtendedDto::getId));
        }
    }

    private boolean needSort(ProductSortType sort, ProductSortType typeToCompare) {
        return sort != null && sort.equals(typeToCompare);
    }

}


