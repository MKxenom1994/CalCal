package com.example.calorie_calculator.service;

import com.example.calorie_calculator.dto.UserDailyInfoDTO;
import com.example.calorie_calculator.model.AppUser;
import com.example.calorie_calculator.model.DailyConsumption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDailyConsumptionService {

    private final DailyConsumptionService dailyConsumptionService;
    private final AppUserService appUserService;

    public List<UserDailyInfoDTO> getAllUsersDailyInfo(LocalDate date) {
        List<AppUser> users = appUserService.getAllUsers();
        List<UserDailyInfoDTO> userDailyInfos = new ArrayList<>();

        for (AppUser user : users) {
            List<DailyConsumption> consumptions = dailyConsumptionService.getDailyConsumptionsByUserAndDate(user, date);
            UserDailyInfoDTO dailyInfo = calculateDailyInfo(user, consumptions);
            userDailyInfos.add(dailyInfo);
        }

        return userDailyInfos;
    }

    private UserDailyInfoDTO calculateDailyInfo(AppUser user, List<DailyConsumption> consumptions) {
        int totalCalories = 0;
        int totalProteins = 0;
        int totalFats = 0;
        int totalCarbohydrates = 0;

        for (DailyConsumption consumption : consumptions) {
            totalCalories += consumption.getCalories();
            totalProteins += consumption.getProteins();
            totalFats += consumption.getFats();
            totalCarbohydrates += consumption.getCarbohydrates();
        }

        return new UserDailyInfoDTO(
                user.getId(),
                user.getUsername(),
                user.getDailyCalorieLimit(),
                totalCalories,
                totalProteins,
                totalFats,
                totalCarbohydrates
        );
    }
}



