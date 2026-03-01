package com.fatihsengun.dto;

import com.fatihsengun.entity.User;
import jakarta.persistence.Column;

import java.time.LocalDate;

public class DtoWorkoutProgramUI {

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean isActive;
}
