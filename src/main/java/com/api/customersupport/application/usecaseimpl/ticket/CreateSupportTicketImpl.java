package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.application.gateway.ticket.CreateSupportTicketGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.TicketSupportNotCreatedException;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.usecases.ticket.CreateSupportTicketUseCase;

public class CreateSupportTicketImpl implements CreateSupportTicketUseCase {
    // Dependency Injection
    private final CreateSupportTicketGateway createSupportTicketGateway;

    public CreateSupportTicketImpl(CreateSupportTicketGateway createSupportTicketGateway) {
        this.createSupportTicketGateway = createSupportTicketGateway;
    }

    @Override
    public void createSupportTicket(SupportTicket supportTicket) throws TicketSupportNotCreatedException {
        boolean success = createSupportTicketGateway.createSupportTicket(supportTicket);
        if (!success) {
            throw new TicketSupportNotCreatedException(ErrorCodeEnum.TS0001.getCode(), ErrorCodeEnum.TS0001.getMessage());
        }
    }
}
