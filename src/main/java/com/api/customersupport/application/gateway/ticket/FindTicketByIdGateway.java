package com.api.customersupport.application.gateway.ticket;

import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.domain.models.SupportTicket;

public interface FindTicketByIdGateway {
    SupportTicket findTicketById(Long ticketId) throws TicketSupportNotFoundException;
}
