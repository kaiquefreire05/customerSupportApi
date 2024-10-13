package com.api.customersupport.infrastructure.services;

import com.api.customersupport.application.gateway.ticket.ListSupportTicketsGateway;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import com.api.customersupport.infrastructure.mapper.SupportTicketMapper;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;

import java.util.List;

public class ListSupportTicketsGatewayImpl implements ListSupportTicketsGateway {
    // Dependency Injection
    private final SupportTicketRepository supportTicketRepository;
    private final SupportTicketMapper supportTicketMapper;

    public ListSupportTicketsGatewayImpl(SupportTicketRepository supportTicketRepository
            , SupportTicketMapper supportTicketMapper) {
        this.supportTicketRepository = supportTicketRepository;
        this.supportTicketMapper = supportTicketMapper;
    }

    @Override
    public List<SupportTicket> listSupportTickets() {
        List<SupportTicketEntity> supportTicketEntityList = supportTicketRepository.findAll();
        return supportTicketMapper.toDomainModelList(supportTicketEntityList);
    }
}
