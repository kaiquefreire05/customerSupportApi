package com.api.customersupport.application.services.ticket;

import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.application.ports.input.ticket.FindTicketByIdUseCase;

public class FindTicketByIdService implements FindTicketByIdUseCase {
    // Dependency Injection
    private final TicketRepositoryPort ticketRepositoryPort;

    public FindTicketByIdService(TicketRepositoryPort ticketRepositoryPort) {
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    public SupportTicket findTicketById(Long ticketId) {
        return ticketRepositoryPort.getTicketById(ticketId);
    }
}
