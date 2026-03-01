package com.fatihsengun.dto;

import com.fatihsengun.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoFitnessProfile {

    private DtoUser user;

    private Double weightKg;

    private Double heightCm;

    private Integer age;

    private String sportsHistory;

    private String currentGoal;

}
