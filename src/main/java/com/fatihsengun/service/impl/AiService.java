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
                        "Age: %d\n" + // 1. Matches profile.getAge() (int)
                        "Body Weight: %.1f kg\n" + // 2. Matches profile.getWeightKg() (Double)
                        "Height: %.1f cm\n" + // 3. Matches profile.getHeightCm() (Double)
                        "Sports & Training Experience: %s\n" + // 4. Matches profile.getSportsHistory() (String)
                        "Primary Goal: %s\n\n" + // 5. Matches profile.getCurrentGoal() (String)
                        "--- CURRENT REQUEST ---\n" +
                        "Training Frequency: %d days per week\n" + // 6. Matches request.getDaysPerWeek() (int)
                        "Available Equipment: %s\n" + // 7. Matches request.getEquipmentAvailable() (String)
                        "Specific Focus: %s\n\n" + // 8. Matches request.getSpecificFocus() (String)
                        "--- MASTER PROGRAMMING RULES ---\n" +
                        // ... (Rules 1-4)
                        "5. STRICT EQUIPMENT ADHERENCE: Only use exercises possible with: %s. No listed equipment = No exercise.\n" + // 9. Matches request.getEquipmentAvailable() (String)
                        "6. SESSION-SPECIFIC COACHING (CRITICAL): \n" +
                        "   - For every 'smallTips' field, write exactly 3 sentences:\n" +
                        "   - Sentence 1: Give a high-level tactical cue for the FIRST compound movement in this specific session.\n" +
                        "   - Sentence 2: Explain the hypertrophy benefit of the isolation work listed in THIS session.\n" +
                        "   - Sentence 3: A 1-sentence motivational closing that mentions the specific muscle group.\n" +
                        "   - PROHIBITION: Do NOT repeat the same tips across different sessions.",
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
