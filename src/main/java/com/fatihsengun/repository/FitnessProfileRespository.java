package com.fatihsengun.repository;

import com.fatihsengun.entity.FitnessProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FitnessProfileRespository extends JpaRepository<FitnessProfile, UUID> {
}
