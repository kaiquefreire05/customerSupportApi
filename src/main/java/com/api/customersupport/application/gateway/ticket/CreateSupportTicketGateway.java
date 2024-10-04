package com.api.customersupport.application.gateway.ticket;

import com.api.customersupport.domain.models.SupportTicket;

public interface CreateSupportTicketGateway {
    Boolean createSupportTicket(SupportTicket supportTicket);
}
