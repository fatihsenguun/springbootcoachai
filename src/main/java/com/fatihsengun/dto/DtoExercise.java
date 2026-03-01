package com.fatihsengun.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoExercise {

    private String name;

    private Integer targetSets;

    private Integer targetReps;

    private Double startWeightKg;

    private Double targetWeightKg;

    private Integer restDurationSeconds;
}
