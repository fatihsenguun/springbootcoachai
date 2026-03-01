package com.fatihsengun.dto;

import com.fatihsengun.entity.WorkoutProgram;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoWorkoutSessionUI {
    private WorkoutProgram workoutProgram;

    private Integer dayNumber;

    private String name;

    private boolean isCompleted;
}
