package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.ticket.*;
import com.api.customersupport.application.usecaseimpl.ticket.*;
import com.api.customersupport.infrastructure.mapper.SupportTicketMapper;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;
import com.api.customersupport.infrastructure.services.ticket.*;
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
        return new ListSupportTicketsService(supportTicketRepository, supportTicketMapper);
    }

    @Bean
    public FindTicketByIdUseCase findTicketByIdUseCase(FindTicketByIdGateway findTicketByIdGateway) {
        return new FindTicketByIdImpl(findTicketByIdGateway);
    }

    @Bean
    public FindTicketByIdGateway findTicketByIdGateway(SupportTicketRepository supportTicketRepository
            , SupportTicketMapper supportTicketMapper) {
        return new FindTicketByIdService(supportTicketRepository, supportTicketMapper);
    }

    @Bean
    public CreateSupportTicketUseCase createSupportTicketUseCase(CreateSupportTicketGateway createSupportTicketGateway) {
        return new CreateSupportTicketImpl(createSupportTicketGateway);
    }

    @Bean
    public GetTicketsByAgentIdUseCase getTicketsByAgentIdUseCase(GetTicketsByAgentIdGateway getTicketByAgentIdGateway) {
        return new GetTicketsByAgentIdImpl(getTicketByAgentIdGateway);
    }

    @Bean
    public UpdateSupportTicketUseCase updateSupportTicketUseCase(UpdateSupportTicketGateway updateSupportTicketGateway) {
        return new UpdateSupportTicketImpl(updateSupportTicketGateway);
    }

    @Bean
    public UpdateSupportTicketGateway updateSupportTicketGateway(SupportTicketRepository supportTicketRepository
            , SupportTicketMapper supportTicketMapper) {
        return new UpdateSupportTicketService(supportTicketRepository, supportTicketMapper);
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
    public GetTicketsByAgentIdGateway getTicketsByAgentIdGateway(SupportTicketRepository ticketRepository,
                                                                 SupportTicketMapper ticketMapper) {
        return new GetTicketsByAgentIdService(ticketRepository, ticketMapper);
    }

    @Bean
    public ListOpenTicketsWithoutAgentUseCase listOpenTicketsWithoutAgentUseCase(ListOpenTicketsWithoutAgentGateway listOpenTicketsWithoutAgentGateway) {
        return new ListOpenTicketsWithoutAgentImpl(listOpenTicketsWithoutAgentGateway);
    }

    @Bean
    public ListOpenTicketsWithoutAgentGateway listOpenTicketsWithoutAgentGateway(SupportTicketRepository supportTicketRepository,
                                              SupportTicketMapper supportTicketMapper) {
        return new ListOpenTicketsWithoutAgentService(supportTicketRepository, supportTicketMapper);
    }

    @Bean
    public DeleteSupportTicketGateway deleteSupportTicketGateway(SupportTicketRepository supportTicketRepository) {
        return new DeleteSupportTicketService(supportTicketRepository);
    }

    @Bean
    public ListOpenSupportTicketsUseCase listOpenSupportTicketsUseCase(ListOpenSupportTicketsGateway listOpenSupportTicketsGateway) {
        return new ListOpenSupportTicketsImpl(listOpenSupportTicketsGateway);
    }

    @Bean
    public ListOpenSupportTicketsGateway listOpenSupportTicketsGateway(SupportTicketRepository supportTicketRepository,
                                                                       SupportTicketMapper supportTicketMapper) {
        return new ListOpenSupportTicketService(supportTicketRepository, supportTicketMapper);
    }
}