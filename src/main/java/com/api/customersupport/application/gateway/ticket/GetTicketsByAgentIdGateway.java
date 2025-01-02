package com.api.customersupport.application.gateway.ticket;

import com.api.customersupport.domain.models.SupportTicket;

import java.util.List;
import java.util.UUID;

public interface GetTicketsByAgentIdGateway {
    List<SupportTicket> getTicketsByAgentId(UUID agentId);
}
