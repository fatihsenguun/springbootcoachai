package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoWorkoutSession;
import com.fatihsengun.dto.DtoWorkoutSessionUI;
import com.fatihsengun.entity.WorkoutSession;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.repository.WorkoutSessionRepository;
import com.fatihsengun.service.IWorkoutSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutSessionServiceImpl implements IWorkoutSessionService {

    @Autowired
    private WorkoutSessionRepository workoutSessionRepository;

    @Autowired
    private IGlobalMapper globalMapper;

    @Override
    public DtoWorkoutSession save(DtoWorkoutSessionUI dtoWorkoutSessionUI) {

        WorkoutSession workoutSession = globalMapper.toEntityWorkoutSession(dtoWorkoutSessionUI);
        return globalMapper.toDtoWorkoutSession(workoutSessionRepository.save(workoutSession));
    }
}
