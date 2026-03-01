package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoExercise;
import com.fatihsengun.dto.DtoExerciseUI;
import com.fatihsengun.entity.RootResponseEntity;

public interface IRestExcercise {

    public RootResponseEntity<DtoExercise> save(DtoExerciseUI dtoExerciseUI);
}
