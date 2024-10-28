package com.api.customersupport.usecases.ticket;

import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;

public interface CloseSupportTicketUseCase {
    void closeSupportTicket(Long ticketId) throws TicketSupportNotFoundException;
}
