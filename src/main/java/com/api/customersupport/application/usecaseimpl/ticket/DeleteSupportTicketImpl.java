package com.api.customersupport.application.usecaseimpl.ticket;

import com.api.customersupport.application.gateway.ticket.DeleteSupportTicketGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.usecases.ticket.DeleteSupportTicketUseCase;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class DeleteSupportTicketImpl implements DeleteSupportTicketUseCase {
    // Dependency Injection
    private final DeleteSupportTicketGateway deleteSupportTicketGateway;

    public DeleteSupportTicketImpl(DeleteSupportTicketGateway deleteSupportTicketGateway) {
        this.deleteSupportTicketGateway = deleteSupportTicketGateway;
    }

    @Override
    public void deleteSupportTicket(Long ticketId) throws InternalServerErrorException {
        log.info("Starting of ticket deletion::DeleteSupportTicketImpl");
        try {
            boolean success = deleteSupportTicketGateway.deleteSupportTicket(ticketId);
            if (!success) {
                throw new InternalServerErrorException(ErrorCodeEnum.TS0003.getCode(), ErrorCodeEnum.TS0003.getMessage());
            }

        } catch (Exception ex) {
            log.info("Error deleting client with ID: {}. Error details: {}", ticketId, ex.getMessage());
            throw new InternalServerErrorException(ErrorCodeEnum.TS0003.getCode()
                    , ErrorCodeEnum.concatError(ex.getMessage(), ErrorCodeEnum.TS0003));
        }
    }
}
