package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRestWorkoutProgramController;
import com.fatihsengun.dto.DtoGenerateProgram;
import com.fatihsengun.dto.DtoWorkoutProgram;
import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.service.impl.WorkoutProgramServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/program")
public class RestWorkoutProgramControllerImpl extends RestRootResponseController implements IRestWorkoutProgramController {

    @Autowired
    private WorkoutProgramServiceImpl workoutProgramService;

    @Override
    @PostMapping("/generate")
    public RootResponseEntity<DtoWorkoutProgram> generateProgram(@RequestBody DtoGenerateProgram dtoGenerateProgram) {
        return ok(workoutProgramService.generateAndSaveProgram(dtoGenerateProgram));
    }


}
