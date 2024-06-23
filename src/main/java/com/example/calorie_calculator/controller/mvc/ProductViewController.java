package com.example.calorie_calculator.controller.mvc;

import com.example.calorie_calculator.model.Product;
import com.example.calorie_calculator.service.ProductDaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductViewController {

    private final ProductDaoService productDaoService;

    @GetMapping("/all")
    public String products(Model model) {
        var allProducts = productDaoService.getAllProducts();

        model.addAttribute("products", allProducts);
        model.addAttribute("product", new Product());
        return "products";
    }

    @PostMapping("/add")
    public String createProduct(@ModelAttribute Product product) {
        productDaoService.saveProduct(product);
        return "redirect:/products/all";
    }


    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
        productDaoService.updateProduct(id, product);
        return "redirect:/products/all";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model) {
        var product = productDaoService.getProductById(id);

        model.addAttribute("product", product);
        model.addAttribute("products", productDaoService.getAllProducts());
        return "products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productDaoService.deleteProduct(id);
        return "redirect:/products/all";
    }
}

