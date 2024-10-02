package com.api.customersupport.usecases.ticket;

public interface AssignAgentToTicketUseCase {
    void assignAgentToTicket(String ticketId, String agentId);
}
