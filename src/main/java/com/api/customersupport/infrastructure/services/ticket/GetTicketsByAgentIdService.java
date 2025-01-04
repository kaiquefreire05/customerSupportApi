package com.api.customersupport.infrastructure.services.ticket;

import com.api.customersupport.application.gateway.ticket.GetTicketsByAgentIdGateway;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import com.api.customersupport.infrastructure.mapper.SupportTicketMapper;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;

import java.util.List;
import java.util.UUID;

public class GetTicketsByAgentIdService implements GetTicketsByAgentIdGateway {
    // Dependency Injection
    private final SupportTicketRepository ticketRepository;
    private final SupportTicketMapper ticketMapper;

    public GetTicketsByAgentIdService(SupportTicketRepository ticketRepository, SupportTicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public List<SupportTicket> getTicketsByAgentId(UUID agentId) {
        List<SupportTicketEntity> ticketEntities = ticketRepository.getTicketsByAssignedAgentId(agentId);
        return ticketMapper.toDomainModelList(ticketEntities);
    }
}
