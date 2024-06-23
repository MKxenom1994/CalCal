//package com.example.calorie_calculator.dao.repository;
//
//import com.example.calorie_calculator.model.AppUser;
//import com.example.calorie_calculator.model.DailyConsumption;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Repository
//public interface DailyConsumptionJpaRepository extends JpaRepository<DailyConsumption, Long> {
//
//    List<DailyConsumption> findByUser(AppUser user);
//
//    List<DailyConsumption> findByUserAndDate(AppUser user, LocalDate date);
//
//}
//
