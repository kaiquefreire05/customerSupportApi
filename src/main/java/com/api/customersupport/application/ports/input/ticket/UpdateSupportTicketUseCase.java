package com.api.customersupport.application.ports.input.ticket;

import com.api.customersupport.domain.models.SupportTicket;

public interface UpdateSupportTicketUseCase {
    SupportTicket updateSupportTicket(SupportTicket supportTicket);
}
