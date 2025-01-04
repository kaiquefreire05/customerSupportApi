package com.api.customersupport.application.gateway.ticket;

import com.api.customersupport.domain.models.SupportTicket;

import java.util.List;

public interface ListOpenTicketsWithoutAgentGateway {
    List<SupportTicket> listOpenTicketsWithoutAgent();
}
