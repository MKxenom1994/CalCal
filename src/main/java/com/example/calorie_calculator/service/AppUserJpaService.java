//package com.example.calorie_calculator.service;
//
//public class AppUserJpaService {
//     TODO на будущее перейти к сохранение в БД
//
//    private final AppUserRepository appUserRepository;
//
//    @Override
//    public List<AppUser> getAllUsers() {
//        return appUserRepository.findAll();
//    }
//
//    @Override
//    public AppUser saveUser(AppUser user) {
//        return appUserRepository.save(user);
//    }
//
//    @Override
//    public AppUser getUserById(Long id) {
//        return appUserRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден с id " + id));
//    }
//
//    @Override
//    public AppUser updateUser(Long id, AppUser userDetails) {
//        AppUser user = getUserById(id);
//        user.setUsername(userDetails.getUsername());
//        user.setDailyCalorieLimit(userDetails.getDailyCalorieLimit());
//        return appUserRepository.save(user);
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        AppUser user = getUserById(id);
//        appUserRepository.delete(user);
//    }
//
//    @Override
//    public List<AppUser> getByUserAndDate(LocalDate date) {
//        return appUserRepository.findByDate(date);
//    }
//}
