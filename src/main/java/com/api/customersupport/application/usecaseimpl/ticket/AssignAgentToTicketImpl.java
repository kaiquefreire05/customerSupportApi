package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.application.gateway.ticket.AssignAgentToTicketGateway;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.usecases.agent.FindAgentByIdUseCase;
import com.api.customersupport.usecases.ticket.AssignAgentToTicketUseCase;
import com.api.customersupport.usecases.ticket.FindTicketByIdUseCase;
import com.api.customersupport.usecases.ticket.UpdateSupportTicketUseCase;

import java.util.UUID;

public class AssignAgentToTicketImpl implements AssignAgentToTicketUseCase {
    // Dependency Injection
    private final FindTicketByIdUseCase findTicketByIdUseCase;
    private final FindAgentByIdUseCase findAgentByIdUseCase;
    private final AssignAgentToTicketGateway assignAgentToTicketGateway;

    public AssignAgentToTicketImpl(FindTicketByIdUseCase findTicketByIdUseCase, FindAgentByIdUseCase findAgentByIdUseCase
            , AssignAgentToTicketGateway assignAgentToTicketGateway) {
        this.findTicketByIdUseCase = findTicketByIdUseCase;
        this.findAgentByIdUseCase = findAgentByIdUseCase;
        this.assignAgentToTicketGateway = assignAgentToTicketGateway;
    }

    @Override
    public void assignAgentToTicket(Long ticketId, UUID agentId) throws TicketSupportNotFoundException
            , AgentNotFoundException {
        var supportTicket = findTicketByIdUseCase.findTicketById(ticketId);
        var agent = findAgentByIdUseCase.findAgentById(agentId);
        assignAgentToTicketGateway.assignAgentToTicket(supportTicket, agent);
    }
}
