package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.application.gateway.ticket.UpdateSupportTicketGateway;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.usecases.ticket.UpdateSupportTicketUseCase;

public class UpdateSupportTicketImpl implements UpdateSupportTicketUseCase {
    // Dependency Injection
    private final UpdateSupportTicketGateway updateSupportTicketGateway;

    public UpdateSupportTicketImpl(UpdateSupportTicketGateway updateSupportTicketGateway) {
        this.updateSupportTicketGateway = updateSupportTicketGateway;
    }

    @Override
    public SupportTicket updateSupportTicket(SupportTicket supportTicket) {
        return updateSupportTicketGateway.updateSupportTicket(supportTicket);
    }
}
