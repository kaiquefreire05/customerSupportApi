package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.application.gateway.ticket.ListOpenTicketsWithoutAgentGateway;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.usecases.ticket.ListOpenTicketsWithoutAgentUseCase;

import java.util.List;

public class ListOpenTicketsWithoutAgentImpl implements ListOpenTicketsWithoutAgentUseCase {
    // Dependency Injection
    private final ListOpenTicketsWithoutAgentGateway listOpenTicketsWithoutAgentGateway;

    public ListOpenTicketsWithoutAgentImpl(ListOpenTicketsWithoutAgentGateway listOpenTicketsWithoutAgentGateway) {
        this.listOpenTicketsWithoutAgentGateway = listOpenTicketsWithoutAgentGateway;
    }

    @Override
    public List<SupportTicket> listOpenTicketsWithoutAgent() {
        return listOpenTicketsWithoutAgentGateway.listOpenTicketsWithoutAgent();
    }
}
