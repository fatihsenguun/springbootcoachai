package com.fatihsengun.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exercise extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id",nullable = false)
    private WorkoutSession workoutSession;

    private String name;

    private Integer targetSets;
    private Integer targetReps;

    @Column(name = "target_weight_kg")
    private Double tagetWeightKg;

    private Integer restDurationSeconds;


}
