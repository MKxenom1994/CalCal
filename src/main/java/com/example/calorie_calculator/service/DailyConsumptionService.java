package com.example.calorie_calculator.service;

import com.example.calorie_calculator.model.AppUser;
import com.example.calorie_calculator.model.DailyConsumption;
import com.example.calorie_calculator.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyConsumptionService {

    private final DailyConsumptionDaoService dailyConsumptionDaoService;

    public List<DailyConsumption> getAllConsumptions() {
        return dailyConsumptionDaoService.findAll();
    }

    public List<DailyConsumption> getDailyConsumptionsByUser(AppUser user) {
        return dailyConsumptionDaoService.findByUser(user);
    }

    public List<DailyConsumption> getDailyConsumptionsByUserAndDate(AppUser user, LocalDate date) {
        return dailyConsumptionDaoService.findByUserAndDate(user, date);
    }

    public void saveConsumption(DailyConsumption consumption) {
        dailyConsumptionDaoService.save(consumption);
    }

    public DailyConsumption getConsumptionById(Long id) {
        return dailyConsumptionDaoService.findById(id);
    }

    public void deleteConsumption(Long id) {
        dailyConsumptionDaoService.deleteById(id);
    }
}





