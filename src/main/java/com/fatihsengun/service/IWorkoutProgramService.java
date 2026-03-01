package com.fatihsengun.service;

import com.fatihsengun.dto.DtoGenerateProgram;
import com.fatihsengun.dto.DtoWorkoutProgram;

public interface IWorkoutProgramService {

    public DtoWorkoutProgram generateAndSaveProgram(DtoGenerateProgram dtoGenerateProgram);

    public DtoWorkoutProgram getMyProgram();


}
