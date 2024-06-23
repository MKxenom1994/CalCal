package com.example.calorie_calculator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDailyInfoDTO {
    private Long userId;
    private String username;
    private int dailyCalorieLimit;
    private int dailyCaloriesConsumed;
    private int dailyProteinsConsumed;
    private int dailyFatsConsumed;
    private int dailyCarbohydratesConsumed;
}
