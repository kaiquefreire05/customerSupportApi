package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.usecases.ticket.CloseSupportTicketUseCase;
import com.api.customersupport.usecases.ticket.FindTicketByIdUseCase;
import com.api.customersupport.usecases.ticket.UpdateSupportTicketUseCase;

public class CloseSupportTicketImpl implements CloseSupportTicketUseCase {
    // Dependency Injection
    private final FindTicketByIdUseCase findTicketByIdUseCase;
    private final UpdateSupportTicketUseCase updateSupportTicketUseCase;

    public CloseSupportTicketImpl(FindTicketByIdUseCase findTicketByIdUseCase
            , UpdateSupportTicketUseCase updateSupportTicketUseCase) {
        this.findTicketByIdUseCase = findTicketByIdUseCase;
        this.updateSupportTicketUseCase = updateSupportTicketUseCase;
    }

    @Override
    public void closeSupportTicket(Long ticketId) throws TicketSupportNotFoundException {
        var ticketSupport = findTicketByIdUseCase.findTicketById(ticketId);
        ticketSupport.closeTicket();
        updateSupportTicketUseCase.updateSupportTicket(ticketSupport);
    }
}
