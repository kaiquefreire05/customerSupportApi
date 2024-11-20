package com.api.customersupport.application.gateway.ticket;

import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.domain.models.SupportTicket;

public interface AssignAgentToTicketGateway {
    SupportTicket assignAgentToTicket(SupportTicket supportTicket, Agent agent);
}
