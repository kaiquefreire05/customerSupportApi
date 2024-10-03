package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.application.gateway.ticket.FindTicketByIdGateway;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.usecases.ticket.FindTicketByIdUseCase;

public class FindTicketByIdImpl implements FindTicketByIdUseCase {
    // Dependency Injection
    private final FindTicketByIdGateway findTicketByIdGateway;

    public FindTicketByIdImpl(FindTicketByIdGateway findTicketByIdGateway) {
        this.findTicketByIdGateway = findTicketByIdGateway;
    }

    @Override
    public SupportTicket findTicketById(Long ticketId) {
        return findTicketByIdGateway.findTicketById(ticketId);
    }
}
