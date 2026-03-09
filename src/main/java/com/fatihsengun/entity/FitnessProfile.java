package com.fatihsengun.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FitnessProfile extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "weight_kg")
    private Double weightKg;

    @Column(name = "height_cm")
    private Double heightCm;

    @Column(nullable = false)
    private Integer age;

    @Column(name = "sports_history", columnDefinition = "TEXT")
    private String sportsHistory;

    @Column(name = "current_goal", length = 100)
    private String currentGoal;
}
