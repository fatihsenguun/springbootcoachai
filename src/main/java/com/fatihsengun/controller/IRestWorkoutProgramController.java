package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoGenerateProgram;
import com.fatihsengun.dto.DtoWorkoutProgram;
import com.fatihsengun.entity.RootResponseEntity;

public interface
IRestWorkoutProgramController {

    public RootResponseEntity<DtoWorkoutProgram> generateProgram(DtoGenerateProgram dtoGenerateProgram);
}
