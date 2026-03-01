package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRestFitnessProfileController;
import com.fatihsengun.dto.DtoFitnessProfile;
import com.fatihsengun.dto.DtoFitnessProfileUI;
import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.service.impl.FitnessProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fitness_profile")
public class RestFitnessProfileController extends RestRootResponseController implements IRestFitnessProfileController {

    @Autowired
    private FitnessProfileService fitnessProfileService;

    @Override
    @PostMapping("/save")
    public RootResponseEntity<DtoFitnessProfile> save(@RequestBody DtoFitnessProfileUI dtoFitnessProfileUI) {
        return ok(fitnessProfileService.save(dtoFitnessProfileUI));
    }

    @Override
    @GetMapping
    public RootResponseEntity<DtoFitnessProfile> getCurrent() {
        return ok(fitnessProfileService.getCurrent());
    }
}
