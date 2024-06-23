package com.example.calorie_calculator.dao;

import com.example.calorie_calculator.model.AppUser;
import com.example.calorie_calculator.model.DailyConsumption;

import java.time.LocalDate;
import java.util.List;

public interface DailyConsumptionDao {

    List<DailyConsumption> findByUserAndDate(AppUser user, LocalDate date);

    List<DailyConsumption> findByUser(AppUser user);

    void save(DailyConsumption dailyConsumption);

    List<DailyConsumption> findAll();

    DailyConsumption findById(Long id);

    void deleteById(Long id);
}
