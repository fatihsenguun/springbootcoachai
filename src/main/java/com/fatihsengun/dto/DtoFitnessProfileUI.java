package com.fatihsengun.dto;

import com.fatihsengun.entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoFitnessProfileUI {


    private Double weightKg;

    private Double heightCm;

    private Integer age;

    private String sportsHistory;

    private String currentGoal;
}
