package com.fatihsengun.service;

import com.fatihsengun.dto.DtoFitnessProfileUI;
import com.fatihsengun.dto.DtoGenerateProgram;
import com.fatihsengun.dto.DtoWorkoutProgram;
import com.fatihsengun.entity.User;

public interface IWorkoutProgramService {

    public DtoWorkoutProgram generateAndSaveProgram(DtoGenerateProgram dtoGenerateProgram);

    public DtoWorkoutProgram getMyProgram();

    public User updateUserProfile(DtoFitnessProfileUI dtoFitnessProfileUI);



}
