package com.fatihsengun.service;

import com.fatihsengun.dto.DtoWorkoutSession;
import com.fatihsengun.dto.DtoWorkoutSessionUI;

import java.util.UUID;

public interface IWorkoutSessionService {

    public DtoWorkoutSession save(DtoWorkoutSessionUI dtoWorkoutSessionUI);

    public DtoWorkoutSession setCompleted(UUID id);
}
