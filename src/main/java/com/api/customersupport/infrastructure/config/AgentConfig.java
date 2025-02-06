package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.mapper.AgentMapper;
import com.api.customersupport.application.mapper.ClientMapper;
import com.api.customersupport.application.ports.input.agent.*;
import com.api.customersupport.application.ports.input.clients.*;
import com.api.customersupport.application.ports.input.feedback.FindFeedbackByIdUseCase;
import com.api.customersupport.application.ports.output.*;
import com.api.customersupport.application.ports.output.availability.AgentEmailAvailabilityPort;
import com.api.customersupport.application.ports.output.availability.ClientEmailAvailabilityPort;
import com.api.customersupport.application.services.agent.*;
import com.api.customersupport.application.services.client.*;
import com.api.customersupport.application.services.feedback.FindFeedbackByIdService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {

    @Bean
    public FindAgentByEmailUseCase findAgentByEmailUseCase(AgentRepositoryPort repository) {
        return new FindAgentByEmailService(repository);
    }

    @Bean
    public FindAgentByIdUseCase findAgentByIdUseCase(AgentRepositoryPort repository) {
        return new FindAgentByIdService(repository);
    }

    @Bean
    public ListAgentsUseCase listAgentsUseCase(AgentRepositoryPort repository) {
        return new ListAgentsService(repository);
    }

    @Bean
    public DeleteAgentUseCase deleteAgentUseCase(AgentRepositoryPort agentRepositoryPort) {
        return new DeleteAgentService(agentRepositoryPort);
    }

    @Bean
    public CreateAgentUseCase createAgentUseCase(AgentRepositoryPort agentRepositoryPort,
                                                 AgentEmailAvailabilityPort agentEmailAvailabilityPort) {
        return new CreateAgentService(agentRepositoryPort, agentEmailAvailabilityPort);
    }

    @Bean
    public UpdateAgentUseCase updateAgentUseCase(AgentRepositoryPort agentRepositoryPort,
                              AgentEmailAvailabilityPort agentEmailAvailabilityPort,
                              FindAgentByIdUseCase findAgentByIdUseCase, AgentMapper agentMapper) {
        return new UpdateAgentService(agentRepositoryPort, agentEmailAvailabilityPort,
                findAgentByIdUseCase, agentMapper);
    }

    @Bean
    public CreateClientUseCase createClientUseCase(ClientRepositoryPort clientRepositoryPort,
                                                   ClientEmailAvailabilityPort clientEmailAvailabilityPort) {
        return new CreateClientService(clientRepositoryPort, clientEmailAvailabilityPort);
    }

    @Bean
    public FindClientByIdUseCase findClientByIdUseCase(ClientRepositoryPort clientRepositoryPort) {
        return new FindClientByIdService(clientRepositoryPort);
    }

    @Bean
    public ListClientsUseCase listClientsUseCase(ClientRepositoryPort repository) {
        return new ListClientsService(repository);
    }

    @Bean
    public UpdateClientUseCase updateClientUseCase(ClientRepositoryPort repository, FindClientByIdUseCase findClientByIdUseCase,
                                                   ClientMapper clientMapper, ClientEmailAvailabilityPort clientEmailAvailabilityPort) {
        return new UpdateClientService(repository, findClientByIdUseCase, clientMapper, clientEmailAvailabilityPort);
    }

    @Bean
    public DeleteClientUseCase deleteClientUseCase(ClientRepositoryPort repository) {
        return new DeleteClientService(repository);
    }

    @Bean
    public FindFeedbackByIdUseCase findFeedbackByIdUseCase(FeedbackRepositoryPort feedbackRepositoryPort) {
        return new FindFeedbackByIdService(feedbackRepositoryPort);
    }

}
