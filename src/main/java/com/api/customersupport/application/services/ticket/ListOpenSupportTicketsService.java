package com.api.customersupport.application.services.ticket;

import com.api.customersupport.application.gateway.ticket.ListOpenSupportTicketsGateway;
import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.application.ports.input.ticket.ListOpenSupportTicketsUseCase;

import java.util.List;

public class ListOpenSupportTicketsService implements ListOpenSupportTicketsUseCase {
    // Dependency Injection
    private final TicketRepositoryPort ticketRepositoryPort;

    public ListOpenSupportTicketsService(TicketRepositoryPort ticketRepositoryPort) {
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    public List<SupportTicket> listOpenSupportTickets() {
        return ticketRepositoryPort.listOpenTickets();
    }
}
