package com.zazergel.category;

import com.zazergel.category.model.CategoryDto;
import com.zazergel.client.BaseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Map;

@Service
@Slf4j
public class CategoryClient extends BaseClient {

    private static final String API_PREFIX = "/categories";

    @Autowired
    public CategoryClient(@Value("${consumer-service.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .build()
        );
    }

    public ResponseEntity<Object> getAll(Integer from, Integer size) {
        log.info("Вывод списка всех категорий.");

        Map<String, Object> parameters = Map.of(
                "from", from,
                "size", size
        );
        return get("?from={from}&size={size}", parameters);
    }

    public ResponseEntity<Object> getById(Long id) {
        log.info("Вывод категории с id {}.", id);
        return get("/" + id);
    }

    public ResponseEntity<Object> add(CategoryDto categoryDto) {
        log.info("Создание категории {}.", categoryDto);
        return post("", categoryDto);
    }

    public ResponseEntity<Object> update(Long id, CategoryDto categoryDto) {
        log.info("Обновление категории {} с id {}.", categoryDto, id);
        return put("/" + id, categoryDto);
    }

    public void delete(Long id) {
        log.info("Удаление категории с id {}.", id);
        delete("/" + id);
    }

}
