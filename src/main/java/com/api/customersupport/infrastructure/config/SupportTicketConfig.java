package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.ticket.*;
import com.api.customersupport.application.ports.input.ticket.*;
import com.api.customersupport.application.services.ticket.*;
import com.api.customersupport.application.mapper.SupportTicketMapper;
import com.api.customersupport.application.services.ticket.AssignAgentToTicketService;
import com.api.customersupport.application.services.ticket.CloseSupportTicketService;
import com.api.customersupport.application.services.ticket.CreateSupportTicketService;
import com.api.customersupport.application.services.ticket.DeleteSupportTicketService;
import com.api.customersupport.application.services.ticket.FindTicketByIdService;
import com.api.customersupport.application.services.ticket.GetTicketsByAgentIdService;
import com.api.customersupport.application.services.ticket.ListOpenTicketsWithoutAgentService;
import com.api.customersupport.application.services.ticket.ListSupportTicketsService;
import com.api.customersupport.application.services.ticket.UpdateSupportTicketService;
import com.api.customersupport.infrastructure.repositories.SupportTicketRepository;
import com.api.customersupport.infrastructure.services.ticket.*;
import com.api.customersupport.application.ports.input.agent.FindAgentByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupportTicketConfig {

    @Bean
    public ListSupportTicketsUseCase listSupportTicketsUseCase(ListSupportTicketsGateway listSupportTicketsGateway) {
        return new ListSupportTicketsService(listSupportTicketsGateway);
    }

    @Bean
    public ListSupportTicketsGateway listSupportTicketsGateway(SupportTicketRepository supportTicketRepository
            , SupportTicketMapper supportTicketMapper) {
        return new com.api.customersupport.infrastructure.services.ticket.ListSupportTicketsService(supportTicketRepository, supportTicketMapper);
    }

    @Bean
    public FindTicketByIdUseCase findTicketByIdUseCase(FindTicketByIdGateway findTicketByIdGateway) {
        return new FindTicketByIdService(findTicketByIdGateway);
    }

    @Bean
    public FindTicketByIdGateway findTicketByIdGateway(SupportTicketRepository supportTicketRepository
            , SupportTicketMapper supportTicketMapper) {
        return new com.api.customersupport.infrastructure.services.ticket.FindTicketByIdService(supportTicketRepository, supportTicketMapper);
    }

    @Bean
    public CreateSupportTicketUseCase createSupportTicketUseCase(CreateSupportTicketGateway createSupportTicketGateway) {
        return new CreateSupportTicketService(createSupportTicketGateway);
    }

    @Bean
    public GetTicketsByAgentIdUseCase getTicketsByAgentIdUseCase(GetTicketsByAgentIdGateway getTicketByAgentIdGateway) {
        return new GetTicketsByAgentIdService(getTicketByAgentIdGateway);
    }

    @Bean
    public UpdateSupportTicketUseCase updateSupportTicketUseCase(UpdateSupportTicketGateway updateSupportTicketGateway) {
        return new UpdateSupportTicketService(updateSupportTicketGateway);
    }

    @Bean
    public UpdateSupportTicketGateway updateSupportTicketGateway(SupportTicketRepository supportTicketRepository
            , SupportTicketMapper supportTicketMapper) {
        return new com.api.customersupport.infrastructure.services.ticket.UpdateSupportTicketService(supportTicketRepository, supportTicketMapper);
    }

    @Bean
    public AssignAgentToTicketUseCase assignAgentToTicketUseCase(FindTicketByIdUseCase findTicketByIdUseCase
            , FindAgentByIdUseCase findAgentByIdUseCase, AssignAgentToTicketGateway assignAgentToTicketGateway) {
        return new AssignAgentToTicketService(findTicketByIdUseCase, findAgentByIdUseCase, assignAgentToTicketGateway);
    }

    @Bean
    public CloseSupportTicketUseCase closeSupportTicketUseCase(CloseSupportTicketGateway closeSupportTicketGateway) {
        return new CloseSupportTicketService(closeSupportTicketGateway);
    }

    @Bean
    public DeleteSupportTicketUseCase deleteSupportTicketUseCase(DeleteSupportTicketGateway deleteSupportTicketGateway) {
        return new DeleteSupportTicketService(deleteSupportTicketGateway);
    }

    @Bean
    public GetTicketsByAgentIdGateway getTicketsByAgentIdGateway(SupportTicketRepository ticketRepository,
                                                                 SupportTicketMapper ticketMapper) {
        return new com.api.customersupport.infrastructure.services.ticket.GetTicketsByAgentIdService(ticketRepository, ticketMapper);
    }

    @Bean
    public ListOpenTicketsWithoutAgentUseCase listOpenTicketsWithoutAgentUseCase(ListOpenTicketsWithoutAgentGateway listOpenTicketsWithoutAgentGateway) {
        return new ListOpenTicketsWithoutAgentService(listOpenTicketsWithoutAgentGateway);
    }

    @Bean
    public ListOpenTicketsWithoutAgentGateway listOpenTicketsWithoutAgentGateway(SupportTicketRepository supportTicketRepository,
                                              SupportTicketMapper supportTicketMapper) {
        return new com.api.customersupport.infrastructure.services.ticket.ListOpenTicketsWithoutAgentService(supportTicketRepository, supportTicketMapper);
    }

    @Bean
    public DeleteSupportTicketGateway deleteSupportTicketGateway(SupportTicketRepository supportTicketRepository) {
        return new com.api.customersupport.infrastructure.services.ticket.DeleteSupportTicketService(supportTicketRepository);
    }

    @Bean
    public ListOpenSupportTicketsUseCase listOpenSupportTicketsUseCase(ListOpenSupportTicketsGateway listOpenSupportTicketsGateway) {
        return new ListOpenSupportTicketsService(listOpenSupportTicketsGateway);
    }

    @Bean
    public ListOpenSupportTicketsGateway listOpenSupportTicketsGateway(SupportTicketRepository supportTicketRepository,
                                                                       SupportTicketMapper supportTicketMapper) {
        return new ListOpenSupportTicketService(supportTicketRepository, supportTicketMapper);
    }
}