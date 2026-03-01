package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoFitnessProfile;
import com.fatihsengun.dto.DtoFitnessProfileUI;
import com.fatihsengun.entity.FitnessProfile;
import com.fatihsengun.entity.User;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.repository.FitnessProfileRespository;
import com.fatihsengun.service.IFitnessProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FitnessProfileService implements IFitnessProfileService {

    @Autowired
    private FitnessProfileRespository fitnessProfileRespository;

    @Autowired
    private IGlobalMapper globalMapper;

    @Autowired
    private IdentityService identityService;

    @Override
    public DtoFitnessProfile save(DtoFitnessProfileUI dtoFitnessProfileUI) {
        User user = identityService.getCurrentUser();
        FitnessProfile fitnessProfile = globalMapper.toEntityFitnessProfile(dtoFitnessProfileUI);
        fitnessProfile.setUser(user);

        return globalMapper.toDtoFitnessProfile(fitnessProfileRespository.save(fitnessProfile));
    }

    @Override
    public DtoFitnessProfile getCurrent() {
        User user = identityService.getCurrentUser();
        return globalMapper.toDtoFitnessProfile(user.getFitnessProfile());
    }
}
