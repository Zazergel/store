package com.zazergel.product.repository;

import com.zazergel.product.model.Product;

import java.util.List;

public interface CustomProductRepo {

    List<Product> searchProducts(String text,
                                 Long maxPrice,
                                 String category);
}
