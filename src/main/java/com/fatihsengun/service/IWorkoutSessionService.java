package com.fatihsengun.service;

import com.fatihsengun.dto.DtoWorkoutSession;
import com.fatihsengun.dto.DtoWorkoutSessionUI;

public interface IWorkoutSessionService {

    public DtoWorkoutSession save(DtoWorkoutSessionUI dtoWorkoutSessionUI);
}
