package com.api.customersupport.application.gateway.ticket;

import com.api.customersupport.domain.models.SupportTicket;

public interface GetSupportTicketWithDetailsGateway {
    SupportTicket getSupportTicketWithDetails(Long ticketId);
}
