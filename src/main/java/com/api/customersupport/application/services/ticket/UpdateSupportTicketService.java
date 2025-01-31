package com.api.customersupport.application.services.ticket;

import com.api.customersupport.application.mapper.SupportTicketMapper;
import com.api.customersupport.application.ports.input.ticket.FindTicketByIdUseCase;
import com.api.customersupport.application.ports.input.ticket.UpdateSupportTicketUseCase;
import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.domain.models.SupportTicket;

import java.time.LocalDateTime;

public class UpdateSupportTicketService implements UpdateSupportTicketUseCase {
    // Dependency Injection
    private final TicketRepositoryPort repository;
    private final FindTicketByIdUseCase findTicketByIdUseCase;
    private final SupportTicketMapper ticketMapper;

    public UpdateSupportTicketService(TicketRepositoryPort repository, FindTicketByIdUseCase findTicketByIdUseCase,
                                      SupportTicketMapper ticketMapper) {
        this.repository = repository;
        this.findTicketByIdUseCase = findTicketByIdUseCase;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public SupportTicket updateSupportTicket(SupportTicket supportTicket) throws TicketSupportNotFoundException {
        SupportTicket existentTicket = findTicketByIdUseCase.findTicketById(supportTicket.getId());
        ticketMapper.updateValues(supportTicket, existentTicket);
        existentTicket.setUpdatedAt(LocalDateTime.now());
        return repository.updateTicket(supportTicket);
    }
}
