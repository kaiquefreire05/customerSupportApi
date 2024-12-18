package com.api.customersupport.usecases.ticket;

import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.domain.models.SupportTicket;

public interface FindTicketByIdUseCase {
    SupportTicket findTicketById(Long ticketId) throws TicketSupportNotFoundException;
}
