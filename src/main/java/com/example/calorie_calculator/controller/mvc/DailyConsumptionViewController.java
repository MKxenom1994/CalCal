package com.example.calorie_calculator.controller.mvc;

import com.example.calorie_calculator.model.DailyConsumption;
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

@Controller
@RequestMapping("/consumptions")
@RequiredArgsConstructor
public class DailyConsumptionViewController {

    private final AppUserService appUserService;
    private final ProductService productDaoService;
    private final DailyConsumptionService dailyConsumptionService;

    @GetMapping("/all")
    public String consumptions(Model model) {
        var allConsumptions = dailyConsumptionService.getAllConsumptions();

        model.addAttribute("consumptions", allConsumptions);
        model.addAttribute("consumption", new DailyConsumption());
        model.addAttribute("users", appUserService.getAllUsers());
        model.addAttribute("products", productDaoService.getAllProducts());
        return "consumptions";
    }

//    @PostMapping("/add")
//    public String createConsumption(@ModelAttribute DailyConsumption consumption) {
//        dailyConsumptionService.saveConsumption(consumption);
//        return "redirect:/consumptions/all";
//    }

    @GetMapping("/edit/{id}")
    public String editConsumption(@PathVariable Long id, Model model) {
        var consumption = dailyConsumptionService.getConsumptionById(id);

        model.addAttribute("consumption", consumption);
        model.addAttribute("consumptions", dailyConsumptionService.getAllConsumptions());
        model.addAttribute("users", appUserService.getAllUsers());
        model.addAttribute("products", productDaoService.getAllProducts());
        return "consumptions";
    }

    @GetMapping("/delete/{id}")
    public String deleteConsumption(@PathVariable Long id) {
        dailyConsumptionService.deleteConsumption(id);
        return "redirect:/consumptions/all";
    }
}
