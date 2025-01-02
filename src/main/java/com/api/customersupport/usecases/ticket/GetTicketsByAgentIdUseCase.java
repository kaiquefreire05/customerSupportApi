package com.api.customersupport.usecases.ticket;

import com.api.customersupport.domain.models.SupportTicket;

import java.util.List;
import java.util.UUID;

public interface GetTicketsByAgentIdUseCase {
    List<SupportTicket> getTicketsByAgentId(UUID agentId);
}
