package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.application.gateway.ticket.CloseSupportTicketGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.usecases.ticket.CloseSupportTicketUseCase;
import com.api.customersupport.usecases.ticket.FindTicketByIdUseCase;
import com.api.customersupport.usecases.ticket.UpdateSupportTicketUseCase;

public class CloseSupportTicketImpl implements CloseSupportTicketUseCase {
    // Dependency Injection
    private final CloseSupportTicketGateway closeSupportTicketGateway;

    public CloseSupportTicketImpl(CloseSupportTicketGateway closeSupportTicketGateway) {
        this.closeSupportTicketGateway = closeSupportTicketGateway;
    }

    @Override
    public void closeSupportTicket(Long ticketId) throws InternalServerErrorException {
        boolean success = closeSupportTicketGateway.closeSupportTicket(ticketId);
        if (!success) {
            throw new InternalServerErrorException(ErrorCodeEnum.TS0004.getCode(), ErrorCodeEnum.TS0004.getMessage());
        }
    }
}
