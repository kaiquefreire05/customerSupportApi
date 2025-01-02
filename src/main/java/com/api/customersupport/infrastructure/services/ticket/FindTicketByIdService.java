package com.api.customersupport.infrastructure.services.ticket;

import com.api.customersupport.application.gateway.ticket.FindTicketByIdGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.TicketSupportNotFoundException;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.mapper.SupportTicketMapper;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class FindTicketByIdService implements FindTicketByIdGateway {
    // Dependency Injection
    private final SupportTicketRepository supportTicketRepository;
    private final SupportTicketMapper supportTicketMapper;

    public FindTicketByIdService(SupportTicketRepository supportTicketRepository
            , SupportTicketMapper supportTicketMapper) {
        this.supportTicketRepository = supportTicketRepository;
        this.supportTicketMapper = supportTicketMapper;
    }

    @Override
    public SupportTicket findTicketById(Long ticketId) throws TicketSupportNotFoundException {
        log.info("Finding ticket by id: {}::FindTicketByIdGatewayImpl", ticketId);
        return supportTicketRepository.findById(ticketId)
                .map(supportTicketMapper::toDomainModel)
                .orElseThrow(() -> new TicketSupportNotFoundException(ErrorCodeEnum.TS0002.getCode()
                        , ErrorCodeEnum.TS0002.getMessage()));
    }
}
