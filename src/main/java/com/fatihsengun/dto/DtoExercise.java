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

    private DtoWorkoutSession dtoWorkoutSession;

    private String name;

    private Integer targetSets;

    private Integer targetReps;

    private Double tagetWeightKg;

    private Integer restDurationSeconds;
}
