package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoExercise;
import com.fatihsengun.dto.DtoGenerateProgram;
import com.fatihsengun.dto.DtoWorkoutProgram;
import com.fatihsengun.dto.DtoWorkoutSession;
import com.fatihsengun.entity.*;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.repository.WorkoutProgramRepository;
import com.fatihsengun.service.IWorkoutProgramService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkoutProgramServiceImpl implements IWorkoutProgramService {


    @Autowired
    private IdentityService identityService;

    @Autowired
    private AiService aiService;

    @Autowired
    private WorkoutProgramRepository workoutProgramRepository;

    @Autowired
    private IGlobalMapper globalMapper;


    public void deactivateOtherPrograms(UUID userId) {
        Optional<WorkoutProgram> activeProgram = workoutProgramRepository.findByUserIdAndIsActiveTrue(userId);

        if (activeProgram.isPresent()) {
            activeProgram.get().setActive(false);
            workoutProgramRepository.save(activeProgram.get());
        }
    }


    @Override
    @Transactional
    public DtoWorkoutProgram generateAndSaveProgram(DtoGenerateProgram dtoGenerateProgram) {

        User user = identityService.getCurrentUser();
        FitnessProfile profile = user.getFitnessProfile();

        deactivateOtherPrograms(user.getId());


        if (profile == null) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "You must create fitness prifle before"));
        }
        DtoWorkoutProgram aiGeneratedDto = aiService.askAiForWorkout(profile, dtoGenerateProgram);

        WorkoutProgram program = globalMapper.toEntityWorkoutProgram(aiGeneratedDto);

        program.setUser(user);
        program.setActive(true);
        program.setStartDate(LocalDate.now());
        program.setAiGeneratedAdvice(aiGeneratedDto.getAiGeneratedAdvice());
        program.setEndDate(LocalDate.now().plusWeeks(4));

        program.getSessions().forEach(session -> {
            session.setWorkoutProgram(program);
            session.getExercises().forEach(ex -> ex.setWorkoutSession(session));
        });

        workoutProgramRepository.save(program);
        return aiGeneratedDto;
    }

    @Override
    public DtoWorkoutProgram getMyProgram() {
        User user = identityService.getCurrentUser();

        WorkoutProgram activeProgram = workoutProgramRepository.findByUserIdAndIsActiveTrue(user.getId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST,
                        "No active workout program found")));

        return globalMapper.toDtoWorkoutProgram(activeProgram);


    }
}
