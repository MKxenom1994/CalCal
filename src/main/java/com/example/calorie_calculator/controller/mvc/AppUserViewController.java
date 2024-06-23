package com.example.calorie_calculator.controller.mvc;

import com.example.calorie_calculator.model.AppUser;
import com.example.calorie_calculator.model.DailyConsumption;
import com.example.calorie_calculator.model.Product;
import com.example.calorie_calculator.service.AppUserService;
import com.example.calorie_calculator.service.DailyConsumptionService;
import com.example.calorie_calculator.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class AppUserViewController {

    private final AppUserService appUserService;
    private final ProductService productService;
    private final DailyConsumptionService dailyConsumptionService;

    @GetMapping("/all")
    public String users(Model model) {
        var allUsers = appUserService.getAllUsers();

        model.addAttribute("users", allUsers);
        model.addAttribute("user", new AppUser());
        return "users";
    }

    @PostMapping("/add")
    public String createUser(@ModelAttribute AppUser user) {
        appUserService.saveUser(user);
        return "redirect:/users/all";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {
        var user = appUserService.getUserById(id);

        model.addAttribute("user", user);
        model.addAttribute("users", appUserService.getAllUsers());
        return "users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
        return "redirect:/users/all";
    }

    @GetMapping("/detail/{id}")
    public String userDetail(@PathVariable Long id, Model model) {
        AppUser user = appUserService.getUserById(id);
        List<DailyConsumption> dailyConsumptions = dailyConsumptionService.getDailyConsumptionsByUser(user);
        List<Product> products = productService.getAllProducts();

        model.addAttribute("user", user);
        model.addAttribute("dailyConsumptions", dailyConsumptions);
        model.addAttribute("products", products);
        model.addAttribute("newConsumption", new DailyConsumption());
        return "user-detail";
    }

    @PostMapping("/detail/add/{userId}")
    public String addConsumption(@PathVariable Long userId, @ModelAttribute DailyConsumption newConsumption) {
        AppUser user = appUserService.getUserById(userId);
        Product product = productService.getProductById(newConsumption.getProduct().getId());

        newConsumption.setUser(user);
        newConsumption.setProduct(product);

        dailyConsumptionService.saveConsumption(newConsumption);
        return "redirect:/users/detail/" + user.getId();
    }
}
