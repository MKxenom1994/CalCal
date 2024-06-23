//package com.example.calorie_calculator.service;
//
//public class DailyConsumptionJpaService {
//
//     TODO на будущее перейти к сохранение в БД
//
//    private final DailyConsumptionRepository dailyConsumptionRepository;
//
//    @Override
//    public List<DailyConsumption> findByUserAndDate(AppUser user, LocalDate date) {
//        return dailyConsumptionRepository.findAll().stream()
//                .filter(dc -> dc.getUser().equals(user) && dc.getDate().equals(date))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<DailyConsumption> findByUser(AppUser user) {
//        return dailyConsumptionRepository.findAll().stream()
//                .filter(dc -> dc.getUser().equals(user))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public DailyConsumption save(DailyConsumption dailyConsumption) {
//        return dailyConsumptionRepository.save(dailyConsumption);
//    }
//
//    @Override
//    public List<DailyConsumption> findAll() {
//        return dailyConsumptionRepository.findAll();
//    }
//
//    @Override
//    public DailyConsumption findById(Long id) {
//        return dailyConsumptionRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Потребление не найдено с id " + id));
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        dailyConsumptionRepository.deleteById(id);
//    }
//}
