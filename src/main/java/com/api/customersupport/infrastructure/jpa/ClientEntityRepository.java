package com.api.customersupport.infrastructure.jpa;

import com.api.customersupport.infrastructure.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, UUID> {
    Optional<ClientEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
