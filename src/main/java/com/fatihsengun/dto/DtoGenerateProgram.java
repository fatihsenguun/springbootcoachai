package com.fatihsengun.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoGenerateProgram {

    private Integer daysPerWeek;
    private String equipmentAvailable;// e.g., "Full Gym", "Dumbbells Only", "Bodyweight"
    private String specificFocus;// e.g., "Build chest", "Lose belly fat"

}
