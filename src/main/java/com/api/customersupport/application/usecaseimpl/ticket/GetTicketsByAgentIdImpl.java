package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.application.gateway.ticket.GetTicketsByAgentIdGateway;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.usecases.ticket.GetTicketsByAgentIdUseCase;

import java.util.List;
import java.util.UUID;

public class GetTicketsByAgentIdImpl implements GetTicketsByAgentIdUseCase {
    // Dependency Injection
    private final GetTicketsByAgentIdGateway getTicketByAgentIdGateway;

    public GetTicketsByAgentIdImpl(GetTicketsByAgentIdGateway getTicketByAgentIdGateway) {
        this.getTicketByAgentIdGateway = getTicketByAgentIdGateway;
    }

    @Override
    public List<SupportTicket> getTicketsByAgentId(UUID agentId) {
        return getTicketByAgentIdGateway.getTicketsByAgentId(agentId);
    }
}
