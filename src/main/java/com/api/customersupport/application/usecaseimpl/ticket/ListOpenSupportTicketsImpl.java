package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.application.gateway.ticket.ListOpenSupportTicketsGateway;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.usecases.ticket.ListOpenSupportTicketsUseCase;

import java.util.List;

public class ListOpenSupportTicketsImpl implements ListOpenSupportTicketsUseCase {
    // Dependency Injection
    private final ListOpenSupportTicketsGateway listOpenSupportTicketsGateway;

    public ListOpenSupportTicketsImpl(ListOpenSupportTicketsGateway listOpenSupportTicketsGateway) {
        this.listOpenSupportTicketsGateway = listOpenSupportTicketsGateway;
    }

    @Override
    public List<SupportTicket> listOpenSupportTickets() {
        return listOpenSupportTicketsGateway.listOpenSupportTickets();
    }
}
