package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoExercise;
import com.fatihsengun.dto.DtoExerciseUI;
import com.fatihsengun.entity.Exercise;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.repository.ExerciseRepository;
import com.fatihsengun.service.IExcerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements IExcerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private IGlobalMapper globalMapper;

    @Override
    public DtoExercise save(DtoExerciseUI dtoExerciseUI) {
        Exercise exercise = exerciseRepository.save(globalMapper.toEntityExercise(dtoExerciseUI));
        return globalMapper.toDtoExercise(exercise);
    }
}
