package com.fatihsengun.service;

import com.fatihsengun.dto.DtoExercise;
import com.fatihsengun.dto.DtoExerciseUI;

public interface IExcerciseService {
    public DtoExercise save(DtoExerciseUI dtoExerciseUI);
}
