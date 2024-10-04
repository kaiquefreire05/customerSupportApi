package com.api.customersupport.usecases.ticket;

import com.api.customersupport.domain.models.SupportTicket;

public interface GetSupportTicketWithDetailsUseCase {
    SupportTicket getSupportTicketWithDetails(Long ticketId);
}
