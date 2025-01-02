package com.api.customersupport.infrastructure.services.ticket;

import com.api.customersupport.application.gateway.ticket.AssignAgentToTicketGateway;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import com.api.customersupport.infrastructure.mapper.SupportTicketMapper;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;
import org.springframework.stereotype.Service;

@Service
public class AssignAgentToTicketService implements AssignAgentToTicketGateway {
    // Dependency Injection
    private final SupportTicketRepository supportTicketRepository;
    private final SupportTicketMapper supportTicketMapper;

    public AssignAgentToTicketService(SupportTicketRepository supportTicketRepository, SupportTicketMapper supportTicketMapper) {
        this.supportTicketRepository = supportTicketRepository;
        this.supportTicketMapper = supportTicketMapper;
    }

    @Override
    public SupportTicket assignAgentToTicket(SupportTicket supportTicket, Agent agent) {
        supportTicket.setAssignedAgent(agent);
        SupportTicketEntity supportTicketEntity = supportTicketMapper.toEntityUpdate(supportTicket);
        SupportTicketEntity savedSupportTicketEntity = supportTicketRepository.save(supportTicketEntity);
        return supportTicketMapper.toDomainModel(savedSupportTicketEntity);
    }
}
