package com.api.customersupport.usecases.ticket;

import com.api.customersupport.domain.models.SupportTicket;

public interface UpdateSupportTicketUseCase {
    SupportTicket updateSupportTicket(SupportTicket supportTicket);
}
