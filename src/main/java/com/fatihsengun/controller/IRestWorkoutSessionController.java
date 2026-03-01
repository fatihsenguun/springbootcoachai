package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoWorkoutSession;
import com.fatihsengun.dto.DtoWorkoutSessionUI;
import com.fatihsengun.entity.RootResponseEntity;

public interface IRestWorkoutSessionController {

    public RootResponseEntity<DtoWorkoutSession> save(DtoWorkoutSessionUI dtoWorkoutSessionUI);
}
