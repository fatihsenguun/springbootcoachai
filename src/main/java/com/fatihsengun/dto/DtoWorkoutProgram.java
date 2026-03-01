package com.fatihsengun.dto;

import com.fatihsengun.entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoWorkoutProgram {



    private String name;

    private String aiGeneratedAdvice;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<DtoWorkoutSession> sessions;

    private boolean isActive;
}
