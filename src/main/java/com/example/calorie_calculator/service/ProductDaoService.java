package com.example.calorie_calculator.service;

import com.example.calorie_calculator.dao.ProductDao;
import com.example.calorie_calculator.exception.JsonFileException;
import com.example.calorie_calculator.exception.ResourceNotFoundException;
import com.example.calorie_calculator.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDaoService implements ProductDao {

    private final ObjectMapper objectMapper;
    private static final String PRODUCT_NOT_FOUND_MSG = "Продукт не найден с id ";
    private static final String PRODUCT_FILE_PATH = "products.json";

    @Override
    public List<Product> getAllProducts() {
        return readFromFile(new TypeReference<>() {
        });
    }

    @Override
    public Product saveProduct(Product product) {
        List<Product> products = getAllProducts();
        product.setId(products.stream().mapToLong(Product::getId).max().orElse(0L) + 1);
        products.add(product);
        writeToFile(products);
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        return getAllProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND_MSG + id));
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) {
        List<Product> products = getAllProducts();
        Product product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND_MSG + id));
        product.setName(productDetails.getName());
        product.setCalories100g(productDetails.getCalories100g());
        product.setTotalWeight(productDetails.getTotalWeight());
        product.setProteins(productDetails.getProteins());
        product.setFats(productDetails.getFats());
        product.setCarbohydrates(productDetails.getCarbohydrates());
        writeToFile(products);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        List<Product> products = getAllProducts();
        Product product = products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(PRODUCT_NOT_FOUND_MSG + id));
        products.remove(product);
        writeToFile(products);
    }

    private <T> List<T> readFromFile(TypeReference<List<T>> typeReference) {
        try {
            File file = new File(ProductDaoService.PRODUCT_FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file, typeReference);
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            throw new JsonFileException("Ошибка при чтении данных из файла " + ProductDaoService.PRODUCT_FILE_PATH, e);
        }
    }

    private <T> void writeToFile(List<T> data) {
        try {
            objectMapper.writeValue(new File(ProductDaoService.PRODUCT_FILE_PATH), data);
        } catch (IOException e) {
            throw new JsonFileException("Ошибка при записи данных в файл " + ProductDaoService.PRODUCT_FILE_PATH, e);
        }
    }
}
