package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.ticket.*;
import com.api.customersupport.application.usecaseimpl.ticket.*;
import com.api.customersupport.infrastructure.mapper.SupportTicketMapper;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;
import com.api.customersupport.infrastructure.services.ticket.DeleteSupportTicketGatewayImpl;
import com.api.customersupport.infrastructure.services.ticket.FindTicketByIdGatewayImpl;
import com.api.customersupport.infrastructure.services.ticket.ListSupportTicketsGatewayImpl;
import com.api.customersupport.infrastructure.services.ticket.UpdateSupportTicketGatewayImpl;
import com.api.customersupport.usecases.agent.FindAgentByIdUseCase;
import com.api.customersupport.usecases.ticket.*;
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

    @Bean
    public AssignAgentToTicketUseCase assignAgentToTicketUseCase(FindTicketByIdUseCase findTicketByIdUseCase
            , FindAgentByIdUseCase findAgentByIdUseCase, AssignAgentToTicketGateway assignAgentToTicketGateway) {
        return new AssignAgentToTicketImpl(findTicketByIdUseCase, findAgentByIdUseCase, assignAgentToTicketGateway);
    }

    @Bean
    public CloseSupportTicketUseCase closeSupportTicketUseCase(CloseSupportTicketGateway closeSupportTicketGateway) {
        return new CloseSupportTicketImpl(closeSupportTicketGateway);
    }

    @Bean
    public DeleteSupportTicketUseCase deleteSupportTicketUseCase(DeleteSupportTicketGateway deleteSupportTicketGateway) {
        return new DeleteSupportTicketImpl(deleteSupportTicketGateway);
    }

    @Bean
    public DeleteSupportTicketGateway deleteSupportTicketGateway(SupportTicketRepository supportTicketRepository) {
        return new DeleteSupportTicketGatewayImpl(supportTicketRepository);
    }
}
