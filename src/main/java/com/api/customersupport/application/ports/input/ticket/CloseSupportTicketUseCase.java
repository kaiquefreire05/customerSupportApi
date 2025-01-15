package com.api.customersupport.application.ports.input.ticket;

import com.api.customersupport.domain.exceptions.InternalServerErrorException;

public interface CloseSupportTicketUseCase {
    void closeSupportTicket(Long ticketId) throws InternalServerErrorException;
}
