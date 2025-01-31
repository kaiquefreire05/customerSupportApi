package com.api.customersupport.application.services.ticket;

import com.api.customersupport.application.ports.input.ticket.FindTicketByIdUseCase;
import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.domain.models.SupportTicket;

public class FindTicketByIdService implements FindTicketByIdUseCase {
    // Dependency Injection
    private final TicketRepositoryPort ticketRepositoryPort;

    public FindTicketByIdService(TicketRepositoryPort ticketRepositoryPort) {
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    public SupportTicket findTicketById(Long ticketId) throws TicketSupportNotFoundException {
        return ticketRepositoryPort.getTicketById(ticketId);
    }
}
