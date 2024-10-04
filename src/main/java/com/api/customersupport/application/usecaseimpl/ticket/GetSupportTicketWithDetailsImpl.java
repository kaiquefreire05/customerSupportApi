package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.application.gateway.ticket.GetSupportTicketWithDetailsGateway;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.usecases.ticket.GetSupportTicketWithDetailsUseCase;

public class GetSupportTicketWithDetailsImpl implements GetSupportTicketWithDetailsUseCase {
    // Dependency Injection
    private final GetSupportTicketWithDetailsGateway getSupportTicketWithDetailsGateway;

    public GetSupportTicketWithDetailsImpl(GetSupportTicketWithDetailsGateway getSupportTicketWithDetailsGateway) {
        this.getSupportTicketWithDetailsGateway = getSupportTicketWithDetailsGateway;
    }

    @Override
    public SupportTicket getSupportTicketWithDetails(Long ticketId) {
        return getSupportTicketWithDetailsGateway.getSupportTicketWithDetails(ticketId);
    }
}
