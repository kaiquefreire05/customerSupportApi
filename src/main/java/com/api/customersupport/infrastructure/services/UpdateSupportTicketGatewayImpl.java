package com.api.customersupport.infrastructure.services;

import com.api.customersupport.application.gateway.ticket.UpdateSupportTicketGateway;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import com.api.customersupport.infrastructure.mapper.SupportTicketMapper;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;

public class UpdateSupportTicketGatewayImpl implements UpdateSupportTicketGateway {
    // Dependency Injection
    private final SupportTicketRepository supportTicketRepository;
    private final SupportTicketMapper supportTicketMapper;

    public UpdateSupportTicketGatewayImpl(SupportTicketRepository supportTicketRepository
            , SupportTicketMapper supportTicketMapper) {
        this.supportTicketRepository = supportTicketRepository;
        this.supportTicketMapper = supportTicketMapper;
    }

    @Override
    public SupportTicket updateSupportTicket(SupportTicket supportTicket) {
        SupportTicketEntity supportTicketEntity = supportTicketMapper.toEntityUpdate(supportTicket);
        SupportTicketEntity savedSupportTicketEntity = supportTicketRepository.save(supportTicketEntity);
        return supportTicketMapper.toDomainModel(savedSupportTicketEntity);
    }
}
