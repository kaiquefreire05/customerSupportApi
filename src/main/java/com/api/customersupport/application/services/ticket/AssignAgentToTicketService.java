package com.api.customersupport.application.services.ticket;

import com.api.customersupport.application.gateway.ticket.AssignAgentToTicketGateway;
import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.application.ports.input.agent.FindAgentByIdUseCase;
import com.api.customersupport.application.ports.input.ticket.AssignAgentToTicketUseCase;
import com.api.customersupport.application.ports.input.ticket.FindTicketByIdUseCase;

import java.util.UUID;

public class AssignAgentToTicketService implements AssignAgentToTicketUseCase {
    // Dependency Injection
    private final FindTicketByIdUseCase findTicketByIdUseCase;
    private final FindAgentByIdUseCase findAgentByIdUseCase;
    private final TicketRepositoryPort ticketRepositoryPort;

    public AssignAgentToTicketService(FindTicketByIdUseCase findTicketByIdUseCase, FindAgentByIdUseCase findAgentByIdUseCase,
                                      TicketRepositoryPort ticketRepositoryPort) {
        this.findTicketByIdUseCase = findTicketByIdUseCase;
        this.findAgentByIdUseCase = findAgentByIdUseCase;
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    public void assignAgentToTicket(Long ticketId, UUID agentId) throws TicketSupportNotFoundException
            , AgentNotFoundException {
        var supportTicket = findTicketByIdUseCase.findTicketById(ticketId);
        var agent = findAgentByIdUseCase.findAgentById(agentId);
        if (supportTicket != null && agent != null) {
            ticketRepositoryPort.assignTicketToAgent(ticketId, agentId);
        }

    }
}
