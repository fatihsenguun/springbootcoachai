package com.fatihsengun.service;

import com.fatihsengun.dto.DtoFitnessProfile;
import com.fatihsengun.dto.DtoFitnessProfileUI;

public interface IFitnessProfileService {

    public DtoFitnessProfile save(DtoFitnessProfileUI dtoFitnessProfileUI);

    public DtoFitnessProfile getCurrent();
}
