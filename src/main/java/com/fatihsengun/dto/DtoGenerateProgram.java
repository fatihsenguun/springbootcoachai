package com.fatihsengun.dto;

import com.fatihsengun.enums.DayType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoGenerateProgram {

    @NotNull(message = "Please specify how many days you want to train per week.")
    @Min(value = 1, message = "You must train at least 1 day per week.")
    @Max(value = 7, message = "You cannot train more than 7 days per week.")
    private Integer daysPerWeek;

    @NotBlank(message = "Please specify available equipment (e.g., Gym, Dumbbells, Bodyweight).")
    @Size(max = 200)
    private String equipmentAvailable;

    @NotBlank(message = "Please specify a focus (e.g., Muscle Building, Weight Loss).")
    @Size(max = 100)
    private String specificFocus;

    @NotEmpty(message = "Please select your preferred training days.")
    @Size(min = 1, max = 7)
    private List<DayType> trainingDays;

}
