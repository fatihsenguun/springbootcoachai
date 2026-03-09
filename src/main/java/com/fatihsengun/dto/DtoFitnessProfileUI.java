package com.fatihsengun.dto;

import com.fatihsengun.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoFitnessProfileUI {

    @NotNull(message = "Weight is required.")
    @Min(value = 30, message = "Weight must be at least 30kg.")
    @Max(value = 300, message = "Weight cannot exceed 300kg.")
    private Double weightKg;

    @NotNull(message = "Height is required.")
    @Min(value = 50, message = "Height must be at least 50cm.")
    @Max(value = 250, message = "Height cannot exceed 250cm.")
    private Double heightCm;

    @NotNull(message = "Age is required.")
    @Min(value = 13, message = "You must be at least 13 years old.")
    @Max(value = 100, message = "Age cannot exceed 100.")
    private Integer age;

    @NotBlank(message = "Please describe your sports history.")
    @Size(max = 500, message = "Sports history description is too long.")
    private String sportsHistory;

    @NotBlank(message = "A current goal is required.")
    @Size(max = 100)
    private String currentGoal;
}
