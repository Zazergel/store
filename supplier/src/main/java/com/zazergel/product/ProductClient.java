package com.zazergel.product;

import com.zazergel.client.BaseClient;
import com.zazergel.comment.CommentRequestDto;
import com.zazergel.product.enums.ProductSortType;
import com.zazergel.product.model.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ProductClient extends BaseClient {

    private static final String API_PREFIX = "/products";

    @Autowired
    public ProductClient(@Value("${consumer-service.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .build()
        );
    }

    public ResponseEntity<Object> getAll(Integer from, Integer size, ProductSortType sort) {
        log.info("Вывод списка всех продуктов.");

        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size,
                "sort", sort
        );
        return get("?from={from}&size={size}&sort={sort}", parameters);
    }

    public ResponseEntity<Object> getById(Long id) {
        log.info("Вывод продукта с id {}.", id);
        return get("/" + id);
    }

    public ResponseEntity<Object> add(ProductDto productDto) {
        log.info("Создание продукта {}.", productDto);
        return post("", productDto);
    }

    public ResponseEntity<Object> update(Long id, ProductDto productDto) {
        log.info("Обновление продукта {} с id {}.", productDto, id);
        return put("/" + id, productDto);
    }

    public void delete(Long id) {
        log.info("Удаление продукта с id {}.", id);
        delete("/" + id);
    }

    public ResponseEntity<Object> search(String text,
                                         Long maxPrice,
                                         String category,
                                         ProductSortType sort,
                                         Integer from, Integer size) {
        log.info("Поиск продукта с подстрокой \"{}\".", text);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("text", text);
        parameters.put("maxPrice", maxPrice);
        parameters.put("category", category);
        parameters.put("sort", sort);
        parameters.put("from", from);
        parameters.put("size", size);

        return get("/search?text={text}&maxPrice={maxPrice}&" +
                   "category={category}&sort={sort}&from={from}&size={size}",
                parameters);
    }

    public ResponseEntity<Object> addComment(Long id, CommentRequestDto commentDto) {
        log.info("Добавление комментария продукту с id {}.", id);
        return post("/" + id + "/comment", commentDto);
    }


}
