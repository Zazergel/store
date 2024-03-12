package com.zazergel.product.repository;

import com.zazergel.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long>, CustomProductRepo {
    @EntityGraph(attributePaths = {"category", "comments"})
    @Query("SELECT p FROM Product p")
    List<Product> findAll();

    @Query("SELECT p " +
           "FROM Product p " +
           "WHERE (UPPER(p.name) LIKE UPPER(CONCAT('%', ?1, '%') ) " +
           "OR UPPER(p.description) LIKE UPPER(CONCAT('%', ?1, '%')))")
    Page<Product> search(String text, Pageable pageable);
}
