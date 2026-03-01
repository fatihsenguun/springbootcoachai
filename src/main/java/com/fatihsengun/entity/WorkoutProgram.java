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
public class WorkoutProgram extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String aiGeneratedAdvice;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean isActive;

    @OneToMany(mappedBy = "workoutProgram", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WorkoutSession> sessions = new ArrayList<>();


}
