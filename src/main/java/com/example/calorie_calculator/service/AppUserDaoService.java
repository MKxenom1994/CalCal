package com.example.calorie_calculator.service;

import com.example.calorie_calculator.dao.AppUserDao;
import com.example.calorie_calculator.exception.JsonFileException;
import com.example.calorie_calculator.exception.ResourceNotFoundException;
import com.example.calorie_calculator.model.AppUser;
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
public class AppUserDaoService implements AppUserDao {

    private final ObjectMapper objectMapper;
    private static final String USER_NOT_FOUND_MSG = "Пользователь не найден с id ";
    private static final String USER_FILE_PATH = "users.json";

    @Override
    public List<AppUser> getAllUsers() {
        return readFromFile(new TypeReference<>() {
        });
    }

    @Override
    public AppUser saveUser(AppUser user) {
        List<AppUser> users = getAllUsers();
        user.setId(users.stream().mapToLong(AppUser::getId).max().orElse(0L) + 1);
        users.add(user);
        writeToFile(users);
        return user;
    }

    @Override
    public AppUser getUserById(Long id) {
        return getAllUsers().stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MSG + id));
    }

    @Override
    public AppUser updateUser(Long id, AppUser userDetails) {
        List<AppUser> users = getAllUsers();
        AppUser user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MSG + id));
        user.setUsername(userDetails.getUsername());
        user.setDailyCalorieLimit(userDetails.getDailyCalorieLimit());
        user.setDailyProteinsLimit(userDetails.getDailyProteinsLimit());
        user.setDailyFatesLimit(userDetails.getDailyFatesLimit());
        user.setDailyCarbohydratesLimit(userDetails.getDailyCarbohydratesLimit());
        writeToFile(users);
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        List<AppUser> users = getAllUsers();
        AppUser user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_MSG + id));
        users.remove(user);
        writeToFile(users);
    }

    @Override
    public List<AppUser> getByUserAndDate(LocalDate date) {
        List<AppUser> users = getAllUsers();
        return users.stream()
                .filter(user -> user.getDate().equals(date))
                .toList();
    }

    private <T> List<T> readFromFile(TypeReference<List<T>> typeReference) {
        try {
            File file = new File(AppUserDaoService.USER_FILE_PATH);
            if (file.exists()) {
                return objectMapper.readValue(file, typeReference);
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            throw new JsonFileException("Ошибка при чтении данных из файла " + AppUserDaoService.USER_FILE_PATH, e);
        }
    }

    private <T> void writeToFile(List<T> data) {
        try {
            objectMapper.writeValue(new File(AppUserDaoService.USER_FILE_PATH), data);
        } catch (IOException e) {
            throw new JsonFileException("Ошибка при записи данных в файл " + AppUserDaoService.USER_FILE_PATH, e);
        }
    }
}

