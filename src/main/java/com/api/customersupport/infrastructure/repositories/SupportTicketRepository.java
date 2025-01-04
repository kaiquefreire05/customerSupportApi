package com.api.customersupport.infrastructure.repositories;

import com.api.customersupport.domain.enums.StatusEnum;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SupportTicketRepository extends JpaRepository<SupportTicketEntity, Long> {
    List<SupportTicketEntity> findByStatus(StatusEnum status);
    List<SupportTicketEntity> getTicketsByAssignedAgentId(UUID assignedAgentId);
    @Query("SELECT t FROM SupportTicketEntity t WHERE t.status = 'OPEN' AND t.assignedAgent IS NULL")
    List<SupportTicketEntity> listOpenTicketsWithoutAgent();
}
