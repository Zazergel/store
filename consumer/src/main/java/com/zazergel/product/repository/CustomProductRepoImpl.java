package com.zazergel.product.repository;

import com.zazergel.product.model.Product;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class CustomProductRepoImpl implements CustomProductRepo {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Product> searchProducts(String text,
                                        Long maxPrice,
                                        String category) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        productRoot.fetch("comments", JoinType.LEFT);
        criteriaQuery.select(productRoot);

        EntityGraph<Product> entityGraph = entityManager.createEntityGraph(Product.class);
        entityGraph.addAttributeNodes("category");

        List<Predicate> predicates = new ArrayList<>();

        if (text != null && !text.isEmpty()) {
            Predicate name = criteriaBuilder.like(criteriaBuilder
                    .lower(productRoot.get("name")), "%" + text.toLowerCase() + "%");

            Predicate description = criteriaBuilder.like(criteriaBuilder
                    .lower(productRoot.get("description")), "%" + text.toLowerCase() + "%");

            predicates.add(criteriaBuilder.or(name, description));
        }

        if (maxPrice != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(productRoot.get("price"), maxPrice));
        }

        if (category != null && !category.isBlank()) {
            predicates.add(criteriaBuilder.equal(productRoot.get("category").get("name"), category));
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }

        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery)
                .setHint("javax.persistence.fetchgraph", entityGraph);

        return query.getResultList();
    }
}

