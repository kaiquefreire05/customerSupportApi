package com.api.customersupport.application.ports.output;

import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.domain.models.SupportTicket;

import java.util.List;
import java.util.UUID;

public interface TicketRepositoryPort {
    Boolean assignTicketToAgent(Long ticketId, UUID agentId);
    Boolean closeTicket(Long ticketId);
    SupportTicket createTicket(SupportTicket ticket);
    Boolean deleteTicket(Long ticketId);
    SupportTicket getTicketById(Long ticketId) throws TicketSupportNotFoundException;
    List<SupportTicket> listTicketsByAgent(UUID agentId);
    List<SupportTicket> listOpenTickets();
    List<SupportTicket> listOpenTicketsWithoutAgent();
    List<SupportTicket> listTickets();
    SupportTicket updateTicket(SupportTicket ticket);
}
