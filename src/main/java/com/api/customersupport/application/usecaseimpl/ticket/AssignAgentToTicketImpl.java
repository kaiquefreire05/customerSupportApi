package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.usecases.agent.FindAgentByIdUseCase;
import com.api.customersupport.usecases.ticket.AssignAgentToTicketUseCase;
import com.api.customersupport.usecases.ticket.FindTicketByIdUseCase;
import com.api.customersupport.usecases.ticket.UpdateSupportTicketUseCase;

import java.util.UUID;

public class AssignAgentToTicketImpl implements AssignAgentToTicketUseCase {
    // Dependency Injection
    private final FindTicketByIdUseCase findTicketByIdUseCase;
    private final FindAgentByIdUseCase findAgentByIdUseCase;
    private final UpdateSupportTicketUseCase updateSupportTicketUseCase;

    public AssignAgentToTicketImpl(FindTicketByIdUseCase findTicketByIdUseCase, FindAgentByIdUseCase findAgentByIdUseCase
            , UpdateSupportTicketUseCase updateSupportTicketUseCase) {
        this.findTicketByIdUseCase = findTicketByIdUseCase;
        this.findAgentByIdUseCase = findAgentByIdUseCase;
        this.updateSupportTicketUseCase = updateSupportTicketUseCase;
    }

    @Override
    public void assignAgentToTicket(Long ticketId, UUID agentId) {
        var supportTicket = findTicketByIdUseCase.findTicketById(ticketId);
        var agent = findAgentByIdUseCase.findAgentById(agentId);
        supportTicket.setAssignedAgent(agent);
        updateSupportTicketUseCase.updateSupportTicket(supportTicket);
    }
}
