package com.fatihsengun.mapper;

import com.fatihsengun.dto.*;
import com.fatihsengun.entity.Exercise;
import com.fatihsengun.entity.FitnessProfile;
import com.fatihsengun.entity.User;
import com.fatihsengun.entity.WorkoutSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IGlobalMapper {

    DtoRegister toDtoRegister(User user);

    DtoFitnessProfile toDtoFitnessProfile(FitnessProfile fitnessProfile);

    FitnessProfile toEntityFitnessProfile(DtoFitnessProfileUI fitnessProfileUI);

    DtoExercise toDtoExercise(Exercise exercise);

    Exercise toEntityExercise(DtoExerciseUI dtoExerciseUI);

    DtoWorkoutSession toDtoWorkoutSession(WorkoutSession workoutSession);

    WorkoutSession toEntityWorkoutSession(DtoWorkoutSessionUI dtoWorkoutSessionUI);


}
