package com.api.customersupport.application.ports.input.ticket;

import com.api.customersupport.domain.exceptions.TicketSupportNotCreatedException;
import com.api.customersupport.domain.models.SupportTicket;

public interface CreateSupportTicketUseCase {
    void createSupportTicket(SupportTicket supportTicket) throws TicketSupportNotCreatedException;
}
