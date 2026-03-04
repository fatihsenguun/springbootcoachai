package com.fatihsengun.repository;

import com.fatihsengun.entity.WorkoutProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgram, UUID> {

    Optional<WorkoutProgram> findByUserIdAndIsActiveTrue(UUID id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE WorkoutProgram w SET w.isActive = false WHERE w.user.id = :userId")
    void deactivateAllByUserId(UUID userId);
}
