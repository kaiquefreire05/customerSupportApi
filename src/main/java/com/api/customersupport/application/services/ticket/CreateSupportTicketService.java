package com.api.customersupport.application.services.ticket;

import com.api.customersupport.application.ports.input.ticket.CreateSupportTicketUseCase;
import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.domain.models.SupportTicket;

public class CreateSupportTicketService implements CreateSupportTicketUseCase {
    // Dependency Injection
    private final TicketRepositoryPort ticketRepositoryPort;

    public CreateSupportTicketService(TicketRepositoryPort ticketRepositoryPort) {
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    public void createSupportTicket(SupportTicket supportTicket) {
        ticketRepositoryPort.createTicket(supportTicket);
    }
}
