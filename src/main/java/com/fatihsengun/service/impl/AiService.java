package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoGenerateProgram;
import com.fatihsengun.dto.DtoWorkoutProgram;
import com.fatihsengun.entity.FitnessProfile;
import com.fatihsengun.entity.WorkoutProgram;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;

    public AiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem("You are an elite, professional fitness coach AI. Your job is to create highly personalized, structured workout programs. You must reply ONLY in valid JSON format. Do not include introductory or concluding conversational text.")
                .build();
    }

    public DtoWorkoutProgram askAiForWorkout(FitnessProfile profile, DtoGenerateProgram request) {

        String userPrompt = String.format(
                "--- USER PROFILE ---\n" +
                        "Age: %d\n" +
                        "Weight: %.1f kg\n" +
                        "Height: %.1f cm\n" +
                        "Sports History: %s\n" +
                        "Current Goal: %s\n\n" +
                        "--- CURRENT REQUEST ---\n" +
                        "Days per week to train: %d\n" +
                        "Equipment available: %s\n" +
                        "Specific Focus: %s\n\n" +
                        "Create a workout plan based on this data. Name the 'programName' something highly motivational. Provide encouraging 'advice'.",
                profile.getAge(),
                profile.getWeightKg(),
                profile.getHeightCm(),
                profile.getSportsHistory(),
                profile.getCurrentGoal(),
                request.getDaysPerWeek(),
                request.getEquipmentAvailable(),
                request.getSpecificFocus()
        );
        return chatClient.prompt()
                .user(userPrompt)
                .call()
                .entity(DtoWorkoutProgram.class);

    }


}
