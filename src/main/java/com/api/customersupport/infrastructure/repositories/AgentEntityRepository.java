package com.api.customersupport.infrastructure.repositories;

import com.api.customersupport.infrastructure.entities.AgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgentEntityRepository extends JpaRepository<AgentEntity, UUID> {
}
