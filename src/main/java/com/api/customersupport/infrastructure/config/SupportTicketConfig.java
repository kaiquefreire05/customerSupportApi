package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.ticket.CreateSupportTicketGateway;
import com.api.customersupport.application.gateway.ticket.FindTicketByIdGateway;
import com.api.customersupport.application.gateway.ticket.ListSupportTicketsGateway;
import com.api.customersupport.application.gateway.ticket.UpdateSupportTicketGateway;
import com.api.customersupport.application.usecaseimpl.ticket.CreateSupportTicketImpl;
import com.api.customersupport.application.usecaseimpl.ticket.FindTicketByIdImpl;
import com.api.customersupport.application.usecaseimpl.ticket.ListSupportTicketsImpl;
import com.api.customersupport.application.usecaseimpl.ticket.UpdateSupportTicketImpl;
import com.api.customersupport.infrastructure.mapper.SupportTicketMapper;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;
import com.api.customersupport.infrastructure.services.ticket.FindTicketByIdGatewayImpl;
import com.api.customersupport.infrastructure.services.ticket.ListSupportTicketsGatewayImpl;
import com.api.customersupport.infrastructure.services.ticket.UpdateSupportTicketGatewayImpl;
import com.api.customersupport.usecases.ticket.CreateSupportTicketUseCase;
import com.api.customersupport.usecases.ticket.FindTicketByIdUseCase;
import com.api.customersupport.usecases.ticket.ListSupportTicketsUseCase;
import com.api.customersupport.usecases.ticket.UpdateSupportTicketUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupportTicketConfig {

    @Bean
    public ListSupportTicketsUseCase listSupportTicketsUseCase(ListSupportTicketsGateway listSupportTicketsGateway) {
        return new ListSupportTicketsImpl(listSupportTicketsGateway);
    }

    @Bean
    public ListSupportTicketsGateway listSupportTicketsGateway(SupportTicketRepository supportTicketRepository
            , SupportTicketMapper supportTicketMapper) {
        return new ListSupportTicketsGatewayImpl(supportTicketRepository, supportTicketMapper);
    }

    @Bean
    public FindTicketByIdUseCase findTicketByIdUseCase(FindTicketByIdGateway findTicketByIdGateway) {
        return new FindTicketByIdImpl(findTicketByIdGateway);
    }

    @Bean
    public FindTicketByIdGateway findTicketByIdGateway(SupportTicketRepository supportTicketRepository
            , SupportTicketMapper supportTicketMapper) {
        return new FindTicketByIdGatewayImpl(supportTicketRepository, supportTicketMapper);
    }

    @Bean
    public CreateSupportTicketUseCase createSupportTicketUseCase(CreateSupportTicketGateway createSupportTicketGateway) {
        return new CreateSupportTicketImpl(createSupportTicketGateway);
    }

    @Bean
    public UpdateSupportTicketUseCase updateSupportTicketUseCase(UpdateSupportTicketGateway updateSupportTicketGateway) {
        return new UpdateSupportTicketImpl(updateSupportTicketGateway);
    }

    @Bean
    public UpdateSupportTicketGateway updateSupportTicketGateway(SupportTicketRepository supportTicketRepository
            , SupportTicketMapper supportTicketMapper) {
        return new UpdateSupportTicketGatewayImpl(supportTicketRepository, supportTicketMapper);
    }
}
