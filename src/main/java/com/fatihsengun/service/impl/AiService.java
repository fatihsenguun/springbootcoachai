package com.fatihsengun.service.impl;

import com.fatihsengun.dto.DtoGenerateProgram;
import com.fatihsengun.dto.DtoWorkoutProgram;
import com.fatihsengun.entity.FitnessProfile;
import com.fatihsengun.enums.DayType;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

@Service
public class AiService {

    private final ChatClient chatClient;

    public List<LocalDate> calculateExactWorkoutDates(List<DayType> targetDays) {
        List<LocalDate> exactDates = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < 7; i++) {
            String currentDayName = currentDate.getDayOfWeek().name();

            boolean isTrainingDay = targetDays.stream()
                    .anyMatch(dayEnum -> dayEnum.name().equals(currentDayName));

            if (isTrainingDay) {
                for (int week = 0; week < 4; week++) {
                    exactDates.add(currentDate.plusWeeks(week));
                }
            }
            currentDate = currentDate.plusDays(1);
        }
        Collections.sort(exactDates);
        return exactDates;
    }


    public AiService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem("You are an elite, professional fitness coach AI. Your job is to create highly personalized, structured workout programs. You must reply ONLY in valid JSON format. Do not include introductory or concluding conversational text.")
                .build();
    }

    public DtoWorkoutProgram askAiForWorkout(FitnessProfile profile, DtoGenerateProgram request) {

        LocalDate today = LocalDate.now();
        String todayName = today.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        // 2. Determine the days (Fallback to a default split if the user didn't pick any)
        List<DayType> daysToUse = (request.getTrainingDays() != null && !request.getTrainingDays().isEmpty())
                ? request.getTrainingDays()
                : List.of(DayType.MONDAY, DayType.TUESDAY, DayType.THURSDAY, DayType.FRIDAY);

        // 3. EXECUTE YOUR CALCULATOR!
        List<LocalDate> exactWorkoutDates = calculateExactWorkoutDates(daysToUse);

        try {
            String userPrompt = String.format(
                    "--- USER PROFILE ---\n" +
                            "Age: %s\n" +
                            "Body Weight: %s kg\n" +
                            "Height: %s cm\n" +
                            "Sports & Training Experience: %s\n" +
                            "Primary Goal: %s\n\n" +

                            "--- CURRENT REQUEST ---\n" +
                            "Today's Date: %s (%s)\n" +
                            "Training Frequency: %s days per week\n" +
                            "Available Equipment: %s\n" +
                            "Specific Focus: %s\n\n" +

                            "--- 4-WEEK SCHEDULING & PROGRESSION (CRITICAL) ---\n" +
                            "1. 4-WEEK DURATION: You MUST generate exactly 4 weeks of programming.\n" +
                            "2. DAY OF WEEK ASSIGNMENT: Map each session to the days provided in the exact dates list below.\n" +

                            // *** WE UPDATED THIS RULE TO USE YOUR CALCULATED DATES ***
                            "3. CALENDAR MAPPING: I have calculated the exact calendar dates for this program. You MUST generate exactly %d sessions. Map each session's 'scheduledDate' field perfectly to these dates in chronological order:\n" +
                            "EXACT DATES TO USE: %s\n\n" +

                            "4. PROGRESSIVE OVERLOAD: The program must evolve. Week 2, Week 3, and Week 4 must show logical progression from Week 1. Do NOT just copy and paste Week 1 four times.\n\n" +

                            "--- MASTER PROGRAMMING RULES ---\n" +
                            "5. STRICT EQUIPMENT ADHERENCE: Only use exercises possible with: %s. No listed equipment = No exercise.\n" +
                            "6. SESSION-SPECIFIC COACHING (CRITICAL): \n" +
                            "   - For every 'smallTips' field, write exactly 3 sentences:\n" +
                            "   - Sentence 1: Give a high-level tactical cue for the FIRST compound movement in this specific session.\n" +
                            "   - Sentence 2: Explain the hypertrophy benefit of the isolation work listed in THIS session.\n" +
                            "   - Sentence 3: A 1-sentence motivational closing that mentions the specific muscle group.\n" +
                            "   - PROHIBITION: Do NOT repeat the same tips across different sessions.",

                    // Profile Variables
                    profile.getAge(),
                    profile.getWeightKg(),
                    profile.getHeightCm(),
                    profile.getSportsHistory(),
                    profile.getCurrentGoal(),

                    // Request Variables
                    today.toString(),
                    todayName,
                    request.getDaysPerWeek(),
                    request.getEquipmentAvailable(),
                    request.getSpecificFocus(),

                    // *** INJECTING YOUR CALCULATED DATES HERE ***
                    exactWorkoutDates.size(),      // %d -> Tells AI exactly how many sessions to build (e.g., 16)
                    exactWorkoutDates.toString(),  // %s -> Gives AI the literal list of YYYY-MM-DD dates!

                    // Rules Variables
                    request.getEquipmentAvailable()
            );

            System.out.println("========== SENDING PROMPT TO AI ==========");
            System.out.println(userPrompt);
            System.out.println("==========================================");

            return chatClient.prompt()
                    .user(userPrompt)
                    .call()
                    .entity(DtoWorkoutProgram.class);

        } catch (Exception e) {
            System.err.println("❌ AI SERVICE CRASHED! Reason: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to generate AI program: " + e.getMessage());
        }
    }
}