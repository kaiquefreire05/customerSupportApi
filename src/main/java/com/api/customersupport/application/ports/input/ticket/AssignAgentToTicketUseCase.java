package com.api.customersupport.application.ports.input.ticket;

import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;

import java.util.UUID;

public interface AssignAgentToTicketUseCase {
    void assignAgentToTicket(Long ticketId, UUID agentId) throws TicketSupportNotFoundException, AgentNotFoundException;
}
