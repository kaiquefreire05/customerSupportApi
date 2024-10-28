package com.api.customersupport.infrastructure.repositories;

import com.api.customersupport.infrastructure.entities.AgentEntity;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AgentEntityRepository extends JpaRepository<AgentEntity, UUID> {
    Optional<AgentEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
