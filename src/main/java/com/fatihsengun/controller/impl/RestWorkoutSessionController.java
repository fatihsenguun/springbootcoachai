package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRestWorkoutSessionController;
import com.fatihsengun.dto.DtoWorkoutSession;
import com.fatihsengun.dto.DtoWorkoutSessionUI;
import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.service.impl.WorkoutSessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/session")
public class RestWorkoutSessionController extends RestRootResponseController implements IRestWorkoutSessionController {


    @Autowired
    private WorkoutSessionServiceImpl workoutSessionService;

    @Override
    @PostMapping("/save")
    public RootResponseEntity<DtoWorkoutSession> save(@RequestBody DtoWorkoutSessionUI dtoWorkoutSessionUI) {
        return ok(workoutSessionService.save(dtoWorkoutSessionUI));
    }

    @Override
    @GetMapping("/set_completed")
    public RootResponseEntity<DtoWorkoutSession> setCompleted(@RequestParam(name = "id") UUID id) {
        return ok(workoutSessionService.setCompleted(id));
    }
}
