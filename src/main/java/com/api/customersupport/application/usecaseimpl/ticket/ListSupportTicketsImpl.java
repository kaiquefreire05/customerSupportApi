package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.application.gateway.ticket.ListSupportTicketsGateway;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.usecases.ticket.ListSupportTicketsUseCase;

import java.util.List;

public class ListSupportTicketsImpl implements ListSupportTicketsUseCase {
    // Dependency Injection
    private final ListSupportTicketsGateway listSupportTicketsGateway;

    public ListSupportTicketsImpl(ListSupportTicketsGateway listSupportTicketsGateway) {
        this.listSupportTicketsGateway = listSupportTicketsGateway;
    }

    @Override
    public List<SupportTicket> listSupportTickets() {
        return listSupportTicketsGateway.listSupportTickets();
    }
}
