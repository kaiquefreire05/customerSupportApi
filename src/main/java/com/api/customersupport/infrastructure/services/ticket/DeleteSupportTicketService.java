package com.api.customersupport.infrastructure.services.ticket;

import com.api.customersupport.application.gateway.ticket.DeleteSupportTicketGateway;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class DeleteSupportTicketService implements DeleteSupportTicketGateway {
    // Dependency Injection
    private final SupportTicketRepository supportTicketRepository;

    public DeleteSupportTicketService(SupportTicketRepository supportTicketRepository) {
        this.supportTicketRepository = supportTicketRepository;
    }

    @Override
    public boolean deleteSupportTicket(Long ticketId) {
        try {
            log.info("Deleting support ticket with id: {}", ticketId);
            supportTicketRepository.deleteById(ticketId);
            log.info("Support ticket with id: {} deleted successfully", ticketId);
            return true;
        } catch (IllegalArgumentException ex) {
            log.error("Invalid support ticket ID: {}. Error details: {}", ticketId, ex.getMessage());
            return false;

        } catch (Exception ex) {
            log.error("Failed to delete support ticket with id: {}. Error details: {}", ticketId, ex.getMessage());
            return false;
        }
    }
}
