package com.api.customersupport.usecases.ticket;

import java.util.UUID;

public interface AssignAgentToTicketUseCase {
    void assignAgentToTicket(Long ticketId, UUID agentId);
}
