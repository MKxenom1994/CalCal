package com.example.calorie_calculator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Exclude
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private int quantity; // количество продукта в граммах

    @Column
    private LocalDate date;

    // Считает калории для данного потребления
    public int getCalories() {
        return (product.getCalories100g() * quantity) / 100;
    }

    // Считает белки для данного потребления
    public int getProteins() {
        return (product.getProteins() * quantity) / 100;
    }

    // Считает жиры для данного потребления
    public int getFats() {
        return (product.getFats() * quantity) / 100;
    }

    // Считает углеводы для данного потребления
    public int getCarbohydrates() {
        return (product.getCarbohydrates() * quantity) / 100;
    }

    // Метод для получения общего потребления за день для пользователя
    public static DailyConsumptionSummary calculateDailySummary(List<DailyConsumption> consumptions) {
        int totalCalories = 0;
        int totalProteins = 0;
        int totalFats = 0;
        int totalCarbohydrates = 0;

        for (DailyConsumption consumption : consumptions) {
            totalCalories += consumption.getCalories();
            totalProteins += consumption.getProteins();
            totalFats += consumption.getFats();
            totalCarbohydrates += consumption.getCarbohydrates();
        }

        return new DailyConsumptionSummary(totalCalories, totalProteins, totalFats, totalCarbohydrates);
    }

    // Вспомогательный класс для хранения суммарного потребления
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DailyConsumptionSummary {
        private int totalCalories;
        private int totalProteins;
        private int totalFats;
        private int totalCarbohydrates;
    }
}
