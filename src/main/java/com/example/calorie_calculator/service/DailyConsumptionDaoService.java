package com.example.calorie_calculator.service;

import com.example.calorie_calculator.dao.DailyConsumptionDao;
import com.example.calorie_calculator.exception.JsonFileException;
import com.example.calorie_calculator.exception.ResourceNotFoundException;
import com.example.calorie_calculator.model.AppUser;
import com.example.calorie_calculator.model.DailyConsumption;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DailyConsumptionDaoService implements DailyConsumptionDao {

    private final ObjectMapper objectMapper;
    private static final String CONSUMPTION_NOT_FOUND_MSG = "Не нашлось дневновное потребление с id ";
    private static final String CONSUMPTION_FILE_PATH = "consumptions.json";

    @Override
    public List<DailyConsumption> findByUserAndDate(AppUser user, LocalDate date) {
        return readFromFile(new TypeReference<List<DailyConsumption>>() {}).stream()
                .filter(dc -> dc.getUser().equals(user) && dc.getDate().equals(date))
                .toList();
    }

    @Override
    public List<DailyConsumption> findByUser(AppUser user) {
        return readFromFile(new TypeReference<List<DailyConsumption>>() {}).stream()
                .filter(dc -> dc.getUser().equals(user))
                .toList();
    }

    @Override
    public void save(DailyConsumption dailyConsumption) {
        List<DailyConsumption> consumptions = findAll();
        dailyConsumption.setId(consumptions.stream().mapToLong(DailyConsumption::getId).max().orElse(0L) + 1);
        consumptions.add(dailyConsumption);
        writeToFile(consumptions);
    }

    @Override
    public List<DailyConsumption> findAll() {
        return readFromFile(new TypeReference<>() {
        });
    }

    @Override
    public DailyConsumption findById(Long id) {
        return findAll().stream()
                .filter(dc -> dc.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(CONSUMPTION_NOT_FOUND_MSG + id));
    }

    @Override
    public void deleteById(Long id) {
        List<DailyConsumption> consumptions = findAll();
        DailyConsumption consumption = consumptions.stream()
                .filter(dc -> dc.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(CONSUMPTION_NOT_FOUND_MSG + id));
        consumptions.remove(consumption);
        writeToFile(consumptions);
    }

    private <T> List<T> readFromFile(TypeReference<List<T>> typeReference) {
        try {
            File file = new File(DailyConsumptionDaoService.CONSUMPTION_FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file, typeReference);
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            throw new JsonFileException("Ошибка при чтении данных из файла " + DailyConsumptionDaoService.CONSUMPTION_FILE_PATH, e);
        }
    }

    private <T> void writeToFile(List<T> data) {
        try {
            objectMapper.writeValue(new File(DailyConsumptionDaoService.CONSUMPTION_FILE_PATH), data);
        } catch (IOException e) {
            throw new JsonFileException("Ошибка при записи данных в файл " + DailyConsumptionDaoService.CONSUMPTION_FILE_PATH, e);
        }
    }
}

