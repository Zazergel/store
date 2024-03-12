package com.zazergel.product.model;

import com.zazergel.category.model.Category;
import com.zazergel.comment.model.Comment;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Entity
@Table(name = "PRODUCT", schema = "public")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    Long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "id")
    Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    List<Comment> comments;

}