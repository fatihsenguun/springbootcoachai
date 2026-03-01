package com.fatihsengun.controller.impl;

import com.fatihsengun.controller.IRestExcercise;
import com.fatihsengun.dto.DtoExercise;
import com.fatihsengun.dto.DtoExerciseUI;
import com.fatihsengun.entity.RootResponseEntity;
import com.fatihsengun.service.impl.ExerciseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excercise")
public class RestExcerciseController extends RestRootResponseController implements IRestExcercise {

    @Autowired
    private ExerciseServiceImpl exerciseService;

    @Override
    @PostMapping("/save")
    public RootResponseEntity<DtoExercise> save(@RequestBody DtoExerciseUI dtoExerciseUI) {
        return ok(exerciseService.save(dtoExerciseUI));
    }
}
