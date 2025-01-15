package com.api.customersupport.infrastructure.services.ticket;

import com.api.customersupport.application.gateway.ticket.CloseSupportTicketGateway;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.entities.SupportTicketEntity;
import com.api.customersupport.application.mapper.SupportTicketMapper;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CloseSupportTicketService implements CloseSupportTicketGateway {
    // Dependency Injection
    private final SupportTicketRepository ticketRepository;
    private final SupportTicketMapper ticketMapper;

    public CloseSupportTicketService(SupportTicketRepository ticketRepository, SupportTicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public boolean closeSupportTicket(Long ticketId) {
        Optional<SupportTicketEntity> optionalTicketEntity = ticketRepository.findById(ticketId);
        if (optionalTicketEntity.isEmpty()) {
            return false;
        }

        SupportTicket ticket = ticketMapper.toDomainModel(optionalTicketEntity.get());
        if (ticket == null || ticket.isClosed()) {
            return false;
        }

        ticket.closeTicket();
        ticketRepository.save(ticketMapper.toEntityUpdate(ticket));
        return true;
    }
}
