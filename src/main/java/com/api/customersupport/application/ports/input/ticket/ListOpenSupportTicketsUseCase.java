package com.api.customersupport.application.ports.input.ticket;

import com.api.customersupport.domain.models.SupportTicket;

import java.util.List;

public interface ListOpenSupportTicketsUseCase {
    List<SupportTicket> listOpenSupportTickets();
}
