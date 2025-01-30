package com.api.customersupport.application.services.ticket;

import com.api.customersupport.application.ports.input.ticket.CloseSupportTicketUseCase;
import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;

public class CloseSupportTicketService implements CloseSupportTicketUseCase {
    // Dependency Injection
    private final TicketRepositoryPort ticketRepositoryPort;

    public CloseSupportTicketService(TicketRepositoryPort ticketRepositoryPort) {
        this.ticketRepositoryPort = ticketRepositoryPort;
    }

    @Override
    public void closeSupportTicket(Long ticketId) throws InternalServerErrorException {
        boolean success = ticketRepositoryPort.closeTicket(ticketId);
        if (!success) {
            throw new InternalServerErrorException(ErrorCodeEnum.TS0004.getCode(), ErrorCodeEnum.TS0004.getMessage());
        }
    }
}
