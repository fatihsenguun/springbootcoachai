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
                        "Body Weight: %.1f kg\n" +
                        "Height: %.1f cm\n" +
                        "Sports & Training Experience: %s\n" +
                        "Primary Goal: %s\n\n" +
                        "--- CURRENT REQUEST ---\n" +
                        "Training Frequency: %d days per week\n" +
                        "Available Equipment: %s\n" +
                        "Specific Focus: %s\n\n" +
                        "--- MASTER PROGRAMMING RULES ---\n" +
                        "1. SCIENTIFIC GOAL ALIGNMENT:\n" +
                        "   - Strength: 3-5 sets, 3-6 reps, 120-180s rest. Focus on heavy compound multi-joint movements.\n" +
                        "   - Hypertrophy (Muscle Building): 3-4 sets, 8-12 reps, 60-90s rest. Focus on time-under-tension and isolation.\n" +
                        "   - Fat Loss/Conditioning: 3-4 sets, 12-20 reps, 30-45s rest. Focus on metabolic circuits, supersets, and active recovery.\n" +
                        "2. SESSION ANATOMY & NAMING: Provide a descriptive 'name' for every session. Every session MUST start with the heaviest compound movement, followed by accessory work, and ending with isolation.\n" +
                        "3. PROGRAM STRUCTURE & SPLIT: UNLESS 'Full Body' is requested, use a classic split: S1: Chest/Triceps, S2: Back/Biceps, S3: Legs/Shoulders. Repeat or adjust based on Frequency.\n" +
                        "4. INTELLIGENT LOAD & PROGRESSIVE OVERLOAD (CRITICAL):\n" +
                        "   - startWeightKg: Calculate based on 'Body Weight' and 'Experience'. (e.g., 80kg intermediate male = 60kg Squat).\n" +
                        "   - targetWeightKg: This MUST represent the goal for the end of the 4-week program. It MUST NOT be equal to startWeightKg.\n" +
                        "   - CALCULATE: For Compound movements (Squat, Bench, Row), targetWeightKg = startWeightKg + (5 to 10 kg). For Isolation movements (Curls, Lateral Raises), targetWeightKg = startWeightKg + (2.5 to 5 kg).\n" +
                        "5. STRICT EQUIPMENT ADHERENCE: Only use exercises possible with: %s. No listed equipment = No exercise.\n" +
                        "6. PSYCHOLOGICAL COACHING: Write 3 sentences: 1. Acknowledge their sports history. 2. Explain the science of this split. 3. A high-energy closing.\n",
                profile.getAge(),
                profile.getWeightKg(),
                profile.getHeightCm(),
                profile.getSportsHistory(),
                profile.getCurrentGoal(),
                request.getDaysPerWeek(),
                request.getEquipmentAvailable(),
                request.getSpecificFocus(),
                request.getEquipmentAvailable()
        );
        return chatClient.prompt()
                .user(userPrompt)
                .call()
                .entity(DtoWorkoutProgram.class);

    }


}
