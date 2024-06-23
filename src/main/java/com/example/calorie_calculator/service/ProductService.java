package com.example.calorie_calculator.service;

import com.example.calorie_calculator.dao.ProductDao;
import com.example.calorie_calculator.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product saveProduct(Product product) {
        return productDao.saveProduct(product);
    }

    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }

    public Product updateProduct(Long id, Product productDetails) {
        return productDao.updateProduct(id, productDetails);
    }

    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }
}
