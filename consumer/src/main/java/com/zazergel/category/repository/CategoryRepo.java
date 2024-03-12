package com.zazergel.category.repository;

import com.zazergel.category.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    @EntityGraph(attributePaths = {"products"})
    Page<Category> findAll(Pageable pageable);


    @Query(value = "SELECT * FROM CATEGORY с WHERE с.name=:name", nativeQuery = true)
    Optional<Category> findByName(@Param("name") String name);
}
