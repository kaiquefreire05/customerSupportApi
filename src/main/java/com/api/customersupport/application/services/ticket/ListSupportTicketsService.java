package com.api.customersupport.application.services.ticket;

import com.api.customersupport.application.gateway.ticket.ListSupportTicketsGateway;
import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.application.ports.input.ticket.ListSupportTicketsUseCase;

import java.util.List;

public class ListSupportTicketsService implements ListSupportTicketsUseCase {
    // Dependency Injection
    private final TicketRepositoryPort ticketRepositoryPort;

    public ListSupportTicketsService(TicketRepositoryPort ticketRepositoryPort) {
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    public List<SupportTicket> listSupportTickets() {
        return ticketRepositoryPort.listTickets();
    }
}
