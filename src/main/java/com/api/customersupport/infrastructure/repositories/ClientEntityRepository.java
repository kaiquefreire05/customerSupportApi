package com.api.customersupport.infrastructure.repositories;

import com.api.customersupport.infrastructure.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, UUID> {
}
