package com.fatihsengun.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetLog extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "excercise_id", nullable = false)
    private Exercise exercise;

    private Integer setNumber;

    private Integer actualReps;

    @Column(name = "actual_weight_kg")
    private Double actualWeightKg;

}
