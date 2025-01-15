package com.api.customersupport.infrastructure.services.ticket;

import com.api.customersupport.application.gateway.ticket.ListOpenSupportTicketsGateway;
import com.api.customersupport.domain.enums.StatusEnum;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import com.api.customersupport.application.mapper.SupportTicketMapper;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;

import java.util.List;

public class ListOpenSupportTicketService implements ListOpenSupportTicketsGateway {
    // Dependency Injection
    private final SupportTicketRepository supportTicketRepository;
    private final SupportTicketMapper supportTicketMapper;

    public ListOpenSupportTicketService(SupportTicketRepository supportTicketRepository,
                                        SupportTicketMapper supportTicketMapper) {
        this.supportTicketRepository = supportTicketRepository;
        this.supportTicketMapper = supportTicketMapper;
    }

    @Override
    public List<SupportTicket> listOpenSupportTickets() {
        List<SupportTicketEntity> openSupportTicketEntity = supportTicketRepository.findByStatus(StatusEnum.OPEN);
        return supportTicketMapper.toDomainModelList(openSupportTicketEntity);
    }
}
