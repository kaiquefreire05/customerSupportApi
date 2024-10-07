package com.api.customersupport.infrastructure.repositories;

import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportTicketRepository extends JpaRepository<SupportTicketEntity, Long> {
}
