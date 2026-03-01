package com.fatihsengun.dto;

import com.fatihsengun.entity.WorkoutSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoExerciseUI {

    private DtoWorkoutSessionUI workoutSession;

    private String name;

    private Integer targetSets;

    private Integer targetReps;

    private Double tagetWeightKg;

    private Integer restDurationSeconds;

}
