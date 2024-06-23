package com.example.calorie_calculator.dao;

import com.example.calorie_calculator.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts();

    Product saveProduct(Product product);

    Product getProductById(Long id);

    Product updateProduct(Long id, Product productDetails);

    void deleteProduct(Long id);
}
