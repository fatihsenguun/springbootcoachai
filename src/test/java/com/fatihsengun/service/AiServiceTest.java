package com.fatihsengun.service;

import com.fatihsengun.enums.DayType;
import com.fatihsengun.service.impl.AiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AiServiceTest {

    private AiService aiService;

    @BeforeEach
    void setUp() {

        ChatClient.Builder mockBuilder = Mockito.mock(ChatClient.Builder.class);
        ChatClient mockChatClient = Mockito.mock(ChatClient.class);
        Mockito.when(mockBuilder.defaultSystem(Mockito.anyString())).thenReturn(mockBuilder);
        Mockito.when(mockBuilder.build()).thenReturn(mockChatClient);

        aiService = new AiService(mockBuilder);
    }

    @Test
    void testCalculateExactWorkoutDates_WithToday() {
        List<DayType> targetDays = List.of(DayType.MONDAY, DayType.FRIDAY);
        List<LocalDate> result = aiService.calculateExactWorkoutDates(targetDays);

        assertEquals(8, result.size(), "It should generate exactly 8 sessions.");

        for (LocalDate date : result) {
            boolean isMondayOrWednesday =
                    date.getDayOfWeek() == DayOfWeek.MONDAY ||
                            date.getDayOfWeek() == DayOfWeek.FRIDAY;
            assertTrue(isMondayOrWednesday, "Found a date that is NOT Monday or Friday: " + date);
        }
        for (int i = 0; i < result.size() - 1; i++) {
            LocalDate current = result.get(i);
            LocalDate next = result.get(i + 1);

            assertTrue(current.isBefore(next),
                    "Dates are not sorted! " + current + " should be before " + next);
        }
        System.out.println("Generated Dates from Today: " + result);
    }


}
