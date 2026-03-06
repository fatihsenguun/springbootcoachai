package com.fatihsengun.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutSession extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private WorkoutProgram workoutProgram;

    private Integer dayNumber;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String smallTips;

    @Column(name = "scheduled_date")
    private LocalDate scheduledDate;

    private boolean isCompleted = false;

    @OneToMany(mappedBy = "workoutSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> exercises = new ArrayList<>();


}
