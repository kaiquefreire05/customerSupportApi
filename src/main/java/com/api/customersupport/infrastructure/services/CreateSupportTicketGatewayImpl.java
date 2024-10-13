package com.api.customersupport.infrastructure.services;

import com.api.customersupport.application.gateway.ticket.CreateSupportTicketGateway;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.mapper.SupportTicketMapper;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;
import org.springframework.stereotype.Service;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Service
public class CreateSupportTicketGatewayImpl implements CreateSupportTicketGateway {
    // Dependency Injection
    private final SupportTicketRepository supportTicketRepository;
    private final SupportTicketMapper supportTicketMapper;

    public CreateSupportTicketGatewayImpl(SupportTicketRepository supportTicketRepository, SupportTicketMapper supportTicketMapper) {
        this.supportTicketRepository = supportTicketRepository;
        this.supportTicketMapper = supportTicketMapper;
    }

    @Override
    public Boolean createSupportTicket(SupportTicket supportTicket) {
        try {
            log.info("Starting of support ticket creation::CreateSupportTicketGatewayImpl");
            var ticketSaved = supportTicketRepository.save(supportTicketMapper.toEntity(supportTicket));
            log.info("End of support ticket creation::CreateSupportTicketGatewayImpl");
            return true;
        } catch (Exception e) {
            log.error("Error in support ticket creation. Error details: {}::CreateSupportTicketGatewayImpl"
                    , e.getMessage());
            return false;
        }
    }
}
