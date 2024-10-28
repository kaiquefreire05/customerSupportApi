package com.api.customersupport.infrastructure.repositories;

import com.api.customersupport.infrastructure.entities.ClientEntity;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, UUID> {
    Optional<ClientEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
