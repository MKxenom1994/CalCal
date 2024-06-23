package com.example.calorie_calculator.service;

import com.example.calorie_calculator.dao.AppUserDao;
import com.example.calorie_calculator.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserDao appUserDao;

    public List<AppUser> getAllUsers() {
        return appUserDao.getAllUsers();
    }

    public AppUser saveUser(AppUser user) {
        return appUserDao.saveUser(user);
    }

    public AppUser getUserById(Long id) {
        return appUserDao.getUserById(id);
    }

    public AppUser updateUser(Long id, AppUser userDetails) {
        return appUserDao.updateUser(id, userDetails);
    }

    public void deleteUser(Long id) {
        appUserDao.deleteUser(id);
    }

    // Дополнительная бизнес-логика
    public boolean isUserEligibleForReward(Long id) {
        AppUser user = appUserDao.getUserById(id);
        return user.getDailyCalorieLimit() > 2000;
    }

    public void resetDailyCalorieLimit(Long id) {
        AppUser user = appUserDao.getUserById(id);
        user.setDailyCalorieLimit(0);
        appUserDao.saveUser(user);
    }
}
