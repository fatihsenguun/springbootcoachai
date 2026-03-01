package com.fatihsengun.controller;

import com.fatihsengun.dto.DtoFitnessProfile;
import com.fatihsengun.dto.DtoFitnessProfileUI;
import com.fatihsengun.entity.RootResponseEntity;

public interface IRestFitnessProfileController {

    public RootResponseEntity<DtoFitnessProfile> save(DtoFitnessProfileUI dtoFitnessProfileUI);

    public RootResponseEntity<DtoFitnessProfile> getCurrent();
}
