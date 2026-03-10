package com.fatihsengun.dto;

import com.fatihsengun.entity.BaseEntity;
import com.fatihsengun.entity.Exercise;
import com.fatihsengun.entity.WorkoutProgram;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoWorkoutSession  {


    private String id;
    private Integer dayNumber;

    private String name;

    private boolean isCompleted;

    private String smallTips;

    private LocalDate scheduledDate;

    private List<DtoExercise> exercises = new ArrayList<>();
}
