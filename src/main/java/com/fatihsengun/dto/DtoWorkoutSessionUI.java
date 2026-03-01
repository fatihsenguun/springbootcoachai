package com.fatihsengun.dto;

import com.fatihsengun.entity.WorkoutProgram;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoWorkoutSessionUI {

    private Integer dayNumber;

    private String name;

    private List<DtoExerciseUI> exercises;
}
