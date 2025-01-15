package com.api.customersupport.application.services.ticket;

import com.api.customersupport.application.gateway.ticket.GetTicketsByAgentIdGateway;
import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.application.ports.input.ticket.GetTicketsByAgentIdUseCase;

import java.util.List;
import java.util.UUID;

public class GetTicketsByAgentIdService implements GetTicketsByAgentIdUseCase {
    // Dependency Injection
    private final TicketRepositoryPort ticketRepositoryPort;

    public GetTicketsByAgentIdService(TicketRepositoryPort ticketRepositoryPort) {
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    public List<SupportTicket> getTicketsByAgentId(UUID agentId) {
        return ticketRepositoryPort.listTicketsByAgent(agentId);
    }
}
