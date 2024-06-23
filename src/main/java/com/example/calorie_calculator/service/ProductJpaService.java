// TODO на будущее перейти к сохранение в БД
//package com.example.calorie_calculator.service;
//
//public class ProductJpaService {
//
//        private final ProductRepository productRepository;
//
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    public Product saveProduct(Product product) {
//        return productRepository.save(product);
//    }
//
//    public Product getProductById(Long id) {
//        return productRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден с id " + id));
//    }
//
//    public Product updateProduct(Long id, Product productDetails) {
//        Product product = getProductById(id);
//        product.setName(productDetails.getName());
//        return productRepository.save(product);
//    }
//
//    public void deleteProduct(Long id) {
//        Product product = getProductById(id);
//        productRepository.delete(product);
//    }
//}
