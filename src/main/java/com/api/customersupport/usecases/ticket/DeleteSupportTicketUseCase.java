package com.api.customersupport.usecases.ticket;

import com.api.customersupport.domain.exceptions.InternalServerErrorException;

public interface DeleteSupportTicketUseCase {
    void deleteSupportTicket(Long ticketId) throws InternalServerErrorException;
}
