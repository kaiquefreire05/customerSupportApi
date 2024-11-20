package com.api.customersupport.usecases.ticket;

import com.api.customersupport.domain.exceptions.InternalServerErrorException;

public interface CloseSupportTicketUseCase {
    void closeSupportTicket(Long ticketId) throws InternalServerErrorException;
}
