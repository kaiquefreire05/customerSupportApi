package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.mapper.FeedbackMapper;
import com.api.customersupport.application.mapper.SupportTicketMapper;
import com.api.customersupport.application.ports.input.agent.FindAgentByIdUseCase;
import com.api.customersupport.application.ports.input.feedback.*;
import com.api.customersupport.application.ports.input.ticket.*;
import com.api.customersupport.application.ports.output.FeedbackRepositoryPort;
import com.api.customersupport.application.ports.output.TicketRepositoryPort;
import com.api.customersupport.application.services.feedback.DeleteFeedbackService;
import com.api.customersupport.application.services.feedback.FindFeedbackByTicketIdService;
import com.api.customersupport.application.services.feedback.ProvideFeedbackService;
import com.api.customersupport.application.services.feedback.UpdateFeedbackService;
import com.api.customersupport.application.services.ticket.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeedbackConfig {

    @Bean
    public DeleteFeebackUseCase deleteFeebackUseCase(FeedbackRepositoryPort feedbackRepositoryPort) {
        return new DeleteFeedbackService(feedbackRepositoryPort);
    }

    @Bean
    public FindFeedbackByTicketIdUseCase findFeedbackByTicketIdUseCase(FeedbackRepositoryPort feedbackRepositoryPort) {
        return new FindFeedbackByTicketIdService(feedbackRepositoryPort);
    }

    @Bean
    public ProvideFeedbackUseCase provideFeedbackUseCase(FeedbackRepositoryPort repository, FindTicketByIdUseCase findTicketById) {
        return new ProvideFeedbackService(repository, findTicketById);
    }

    @Bean
    public FindTicketByIdUseCase findTicketByIdUseCase(TicketRepositoryPort ticketRepositoryPort) {
        return new FindTicketByIdService(ticketRepositoryPort);
    }

    @Bean
    public UpdateFeedbackUseCase updateFeedbackUseCase(FeedbackRepositoryPort feedbackRepositoryPort, FeedbackMapper feedbackMapper,
                                 FindFeedbackByIdUseCase findFeedbackByIdUseCase) {
        return new UpdateFeedbackService(feedbackRepositoryPort, feedbackMapper, findFeedbackByIdUseCase);
    }

    @Bean
    public AssignAgentToTicketUseCase assignAgentToTicketUseCase(FindTicketByIdUseCase findTicketByIdUseCase,
                                                                 FindAgentByIdUseCase findAgentByIdUseCase,
                                                                 TicketRepositoryPort ticketRepositoryPort) {
        return new AssignAgentToTicketService(findTicketByIdUseCase, findAgentByIdUseCase, ticketRepositoryPort);
    }

    @Bean
    public CloseSupportTicketUseCase closeSupportTicketUseCase(TicketRepositoryPort ticketRepositoryPort) {
        return new CloseSupportTicketService(ticketRepositoryPort);
    }

    @Bean
    public CreateSupportTicketUseCase createSupportTicketUseCase(TicketRepositoryPort ticketRepositoryPort) {
        return new CreateSupportTicketService(ticketRepositoryPort);
    }

    @Bean
    public ListSupportTicketsUseCase listSupportTicketsUseCase(TicketRepositoryPort ticketRepositoryPort) {
        return new ListSupportTicketsService(ticketRepositoryPort);
    }

    @Bean
    public UpdateSupportTicketUseCase updateSupportTicketUseCase(TicketRepositoryPort repository,
                                                                 FindTicketByIdUseCase findTicketByIdUseCase,
                                                                 SupportTicketMapper ticketMapper) {
        return new UpdateSupportTicketService(repository, findTicketByIdUseCase, ticketMapper);
    }

    @Bean
    public DeleteSupportTicketUseCase deleteSupportTicketUseCase(TicketRepositoryPort repository) {
        return new DeleteSupportTicketService(repository);
    }

    @Bean
    public ListOpenSupportTicketsUseCase listOpenSupportTicketsUseCase(TicketRepositoryPort ticketRepositoryPort) {
        return new ListOpenSupportTicketsService(ticketRepositoryPort);
    }

    @Bean
    public GetTicketsByAgentIdUseCase getTicketsByAgentIdUseCase(TicketRepositoryPort ticketRepositoryPort) {
        return new GetTicketsByAgentIdService(ticketRepositoryPort);
    }

    @Bean
    public ListOpenTicketsWithoutAgentUseCase listOpenTicketsWithoutAgentUseCase(TicketRepositoryPort ticketRepositoryPort) {
        return new ListOpenTicketsWithoutAgentService(ticketRepositoryPort);
    }
}
