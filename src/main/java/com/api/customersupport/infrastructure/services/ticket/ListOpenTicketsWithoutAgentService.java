package com.api.customersupport.infrastructure.services.ticket;

import com.api.customersupport.application.gateway.ticket.ListOpenTicketsWithoutAgentGateway;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import com.api.customersupport.application.mapper.SupportTicketMapper;
import com.api.customersupport.infrastructure.jpa.SupportTicketRepository;

import java.util.List;

public class ListOpenTicketsWithoutAgentService implements ListOpenTicketsWithoutAgentGateway {
    // Dependency Injection
    private final SupportTicketRepository supportTicketRepository;
    private final SupportTicketMapper supportTicketMapper;

    public ListOpenTicketsWithoutAgentService(SupportTicketRepository supportTicketRepository,
                                              SupportTicketMapper supportTicketMapper) {
        this.supportTicketRepository = supportTicketRepository;
        this.supportTicketMapper = supportTicketMapper;
    }

    @Override
    public List<SupportTicket> listOpenTicketsWithoutAgent() {
        List<SupportTicketEntity> openSupportTicketEntity = supportTicketRepository.listOpenTicketsWithoutAgent();
        return supportTicketMapper.toDomainModelList(openSupportTicketEntity);
    }
}
