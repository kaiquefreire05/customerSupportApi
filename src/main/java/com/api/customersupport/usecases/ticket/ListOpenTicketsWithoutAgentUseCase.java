package com.api.customersupport.usecases.ticket;

import com.api.customersupport.domain.models.SupportTicket;

import java.util.List;

public interface ListOpenTicketsWithoutAgentUseCase {
    List<SupportTicket> listOpenTicketsWithoutAgent();
}
