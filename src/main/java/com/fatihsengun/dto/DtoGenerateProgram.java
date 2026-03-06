package com.fatihsengun.dto;

import com.fatihsengun.enums.DayType;
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

    private Integer daysPerWeek;
    private String equipmentAvailable;
    private String specificFocus;

    private List<DayType> trainingDays;

}
