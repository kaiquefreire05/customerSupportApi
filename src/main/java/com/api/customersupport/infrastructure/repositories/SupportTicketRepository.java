package com.api.customersupport.infrastructure.repositories;

import com.api.customersupport.domain.enums.StatusEnum;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportTicketRepository extends JpaRepository<SupportTicketEntity, Long> {
    List<SupportTicketEntity> findByStatus(StatusEnum status);
}
