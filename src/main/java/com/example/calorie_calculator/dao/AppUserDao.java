package com.example.calorie_calculator.dao;

import com.example.calorie_calculator.model.AppUser;

import java.time.LocalDate;
import java.util.List;

public interface AppUserDao {

    List<AppUser> getAllUsers();

    AppUser saveUser(AppUser user);

    AppUser getUserById(Long id);

    AppUser updateUser(Long id, AppUser userDetails);

    void deleteUser(Long id);

    List<AppUser> getByUserAndDate(LocalDate date);
}
