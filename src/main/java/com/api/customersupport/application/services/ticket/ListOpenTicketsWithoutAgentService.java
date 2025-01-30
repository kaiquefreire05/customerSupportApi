package com.api.customersupport.application.services.ticket;

import com.api.customersupport.application.ports.input.ticket.ListOpenTicketsWithoutAgentUseCase;
import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.domain.models.SupportTicket;

import java.util.List;

public class ListOpenTicketsWithoutAgentService implements ListOpenTicketsWithoutAgentUseCase {
    // Dependency Injection
    private final TicketRepositoryPort ticketRepositoryPort;

    public ListOpenTicketsWithoutAgentService(TicketRepositoryPort ticketRepositoryPort) {
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    public List<SupportTicket> listOpenTicketsWithoutAgent() {
        return ticketRepositoryPort.listOpenTicketsWithoutAgent();
    }
}
