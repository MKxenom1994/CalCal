package com.example.calorie_calculator.controller.mvc;

import com.example.calorie_calculator.dto.UserDailyInfoDTO;
import com.example.calorie_calculator.service.UserDailyConsumptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final UserDailyConsumptionService userDailyConsumptionService;

    @GetMapping("/")
    public String index(Model model) {
        LocalDate today = LocalDate.now();
        List<UserDailyInfoDTO> userDailyInfos = userDailyConsumptionService.getAllUsersDailyInfo(today);

        model.addAttribute("userDailyInfos", userDailyInfos);
        model.addAttribute("date", today);

        return "index";
    }
}

