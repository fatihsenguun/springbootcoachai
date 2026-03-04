package com.fatihsengun.service.impl;

import com.fatihsengun.dto.*;
import com.fatihsengun.entity.*;
import com.fatihsengun.exception.BaseException;
import com.fatihsengun.exception.ErrorMessage;
import com.fatihsengun.exception.MessageType;
import com.fatihsengun.mapper.IGlobalMapper;
import com.fatihsengun.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private WorkoutProgramRepository workoutProgramRepository;

    @Autowired
    private IGlobalMapper globalMapper;


    public void deactivateOtherPrograms(UUID userId) {
        Optional<WorkoutProgram> activeProgram = workoutProgramRepository.findByUserIdAndIsActiveTrue(userId);
        workoutProgramRepository.deactivateAllByUserId(userId);
        if (activeProgram.isPresent()) {
            activeProgram.get().setActive(false);
            workoutProgramRepository.save(activeProgram.get());
        }
    }

    @Override
    @Transactional
    public User updateUserProfile(DtoFitnessProfileUI dtoFitnessProfileUI) {
        User user = identityService.getCurrentUser();

        FitnessProfile profile = user.getFitnessProfile();

        if (profile == null) {
            profile = new FitnessProfile();
            profile.setUser(user);
            user.setFitnessProfile(profile);
        }

        profile.setWeightKg(dtoFitnessProfileUI.getWeightKg());
        profile.setHeightCm(dtoFitnessProfileUI.getHeightCm());
        profile.setAge(dtoFitnessProfileUI.getAge());
        profile.setCurrentGoal(dtoFitnessProfileUI.getCurrentGoal());

        user.setOnboardingCompleted(true);
        deactivateOtherPrograms(user.getId());

        return userRepository.save(user);

    }

    @Override
    @Transactional
    public DtoWorkoutProgram generateAndSaveProgram(DtoGenerateProgram dtoGenerateProgram) {
        User user = identityService.getCurrentUser();
        user.setOnboardingCompleted(true);
        User savedUser = userRepository.save(user);
        deactivateOtherPrograms(savedUser.getId());

        FitnessProfile fitnessProfile = savedUser.getFitnessProfile();

        DtoWorkoutProgram aiGeneratedDto = aiService.askAiForWorkout(fitnessProfile, dtoGenerateProgram);
        WorkoutProgram program = globalMapper.toEntityWorkoutProgram(aiGeneratedDto);

        program.setUser(savedUser);
        program.setActive(true);
        program.setStartDate(LocalDate.now());
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
