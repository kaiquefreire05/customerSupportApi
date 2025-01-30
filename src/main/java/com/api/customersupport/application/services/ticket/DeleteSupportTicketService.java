package com.api.customersupport.application.services.ticket;

import com.api.customersupport.application.ports.input.ticket.DeleteSupportTicketUseCase;
import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class DeleteSupportTicketService implements DeleteSupportTicketUseCase {
    // Dependency Injection
    private final TicketRepositoryPort repository;

    public DeleteSupportTicketService(TicketRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void deleteSupportTicket(Long ticketId) throws InternalServerErrorException {
        log.info("Starting of ticket deletion::DeleteSupportTicketImpl");
        try {
            boolean success = repository.deleteTicket(ticketId);
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
