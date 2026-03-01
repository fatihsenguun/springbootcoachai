package com.fatihsengun.dto;

import com.fatihsengun.entity.Exercise;
import com.fatihsengun.entity.WorkoutProgram;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoWorkoutSession {


    private Integer dayNumber;

    private String name;

    private boolean isCompleted;

    private List<DtoExercise> exercises = new ArrayList<>();
}
