package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoExercise;
import com.fatihsengun.dto.DtoGenerateProgram;
import com.fatihsengun.dto.DtoWorkoutProgram;
import com.fatihsengun.dto.DtoWorkoutSession;
import com.fatihsengun.entity.*;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.repository.WorkoutProgramRepository;
import com.fatihsengun.service.IWorkoutProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class WorkoutProgramServiceImpl implements IWorkoutProgramService {


    @Autowired
    private IdentityService identityService;

    @Autowired
    private AiService aiService;

    @Autowired
    private WorkoutProgramRepository workoutProgramRepository;


    @Override
    @Transactional
    public DtoWorkoutProgram generateAndSaveProgram(DtoGenerateProgram dtoGenerateProgram) {

        User user = identityService.getCurrentUser();
        FitnessProfile profile = user.getFitnessProfile();


        if (profile == null) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "You must create fitness prifle before"));
        }
        DtoWorkoutProgram aiGeneratedDto = aiService.askAiForWorkout(profile, dtoGenerateProgram);

        WorkoutProgram program = new WorkoutProgram();
        program.setUser(user);
        program.setName(aiGeneratedDto.getName());
        program.setAiGeneratedAdvice(aiGeneratedDto.getAiGeneratedAdvice());
        program.setStartDate(LocalDate.now());
        program.setEndDate(LocalDate.now().plusWeeks(4));
        program.setActive(true);
        program.setSessions(new ArrayList<>());

        if (aiGeneratedDto.getSessions() != null) {
            for (DtoWorkoutSession sessionDto : aiGeneratedDto.getSessions()) {
                WorkoutSession session = new WorkoutSession();
                session.setDayNumber(sessionDto.getDayNumber());
                session.setName(sessionDto.getName());
                session.setCompleted(false);

                session.setWorkoutProgram(program);
                session.setExercises(new ArrayList<>());

                if (sessionDto.getExercises() != null) {
                    for (DtoExercise exerciseDto : sessionDto.getExercises()) {
                        Exercise exercise = new Exercise();
                        exercise.setName(exerciseDto.getName());
                        exercise.setTargetSets(exerciseDto.getTargetSets());
                        exercise.setTargetReps(exerciseDto.getTargetReps());

                        exercise.setTagetWeightKg(exerciseDto.getTagetWeightKg());
                        exercise.setRestDurationSeconds(exerciseDto.getRestDurationSeconds());

                        exercise.setWorkoutSession(session);
                        session.getExercises().add(exercise);
                    }
                }
                program.getSessions().add(session);

            }
        }
        workoutProgramRepository.save(program);
        return aiGeneratedDto;
    }
}
