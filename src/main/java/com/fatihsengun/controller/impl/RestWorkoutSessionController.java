package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRestWorkoutSessionController;
import com.fatihsengun.dto.DtoWorkoutSession;
import com.fatihsengun.dto.DtoWorkoutSessionUI;
import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.service.impl.WorkoutSessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controller")
public class RestWorkoutSessionController extends RestRootResponseController implements IRestWorkoutSessionController {


    @Autowired
    private WorkoutSessionServiceImpl workoutSessionService;

    @Override
    @PostMapping("/save")
    public RootResponseEntity<DtoWorkoutSession> save(DtoWorkoutSessionUI dtoWorkoutSessionUI) {
        return ok(workoutSessionService.save(dtoWorkoutSessionUI));
    }
}
