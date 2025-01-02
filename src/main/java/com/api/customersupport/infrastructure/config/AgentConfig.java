package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.agent.*;
import com.api.customersupport.application.gateway.client.ClientEmailAvailableGateway;
import com.api.customersupport.application.usecaseimpl.agent.*;
import com.api.customersupport.application.usecaseimpl.client.ClientEmailAvailableImpl;
import com.api.customersupport.infrastructure.mapper.AgentMapper;
import com.api.customersupport.infrastructure.repositories.AgentEntityRepository;
import com.api.customersupport.infrastructure.services.agent.AgentEmailAvailableService;
import com.api.customersupport.infrastructure.services.agent.DeleteAgentService;
import com.api.customersupport.infrastructure.services.agent.ListAgentsService;
import com.api.customersupport.usecases.agent.*;
import com.api.customersupport.usecases.client.ClientEmailAvailableUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AgentConfig {

    @Bean
    public AgentEmailAvailableUseCase agentEmailAvailableUseCase(AgentEmailAvailableGateway agentEmailAvailableGateway) {
        return new AgentEmailAvailableImpl(agentEmailAvailableGateway);
    }

    @Bean
    public AgentEmailAvailableGateway agentEmailAvailableGateway(AgentEntityRepository agentEntityRepository) {
        return new AgentEmailAvailableService(agentEntityRepository);
    }

    @Bean
    public FindAgentByEmailUseCase findAgentByEmailUseCase(FindAgentByEmailGateway findAgentByEmailGateway) {
        return new FindAgentByEmailImpl(findAgentByEmailGateway);
    }

    @Bean
    public FindAgentByIdUseCase findAgentByIdUseCase(FindAgentByIdGateway findAgentByIdGateway) {
        return new FindAgentByIdImpl(findAgentByIdGateway);
    }

    @Bean
    public ClientEmailAvailableUseCase clientEmailAvailableUseCase(ClientEmailAvailableGateway clientEmailAvailableGateway) {
        return new ClientEmailAvailableImpl(clientEmailAvailableGateway);
    }

    @Bean
    public ListAgentsUseCase listAgentsUseCase(ListAgentsGateway listAgentsGateway) {
        return new ListAgentsImpl(listAgentsGateway);
    }

    @Bean
    public ListAgentsGateway listAgentsGateway(AgentEntityRepository agentEntityRepository, AgentMapper agentMapper) {
        return new ListAgentsService(agentEntityRepository, agentMapper);
    }

    @Bean
    public DeleteAgentUseCase deleteAgentUseCase(DeleteAgentGateway deleteAgentGateway) {
        return new DeleteAgentImpl(deleteAgentGateway);
    }

    @Bean
    public DeleteAgentGateway deleteAgentGateway(AgentEntityRepository agentEntityRepository) {
        return new DeleteAgentService(agentEntityRepository);
    }

    @Bean
    public CreateAgentUseCase createAgentUseCase(CreateAgentGateway createAgentGateway) {
        return new CreateAgentImpl(createAgentGateway);
    }

    @Bean
    public UpdateAgentUseCase updateAgentUseCase(UpdateAgentGateway updateAgentGateway) {
        return new UpdateAgentImpl(updateAgentGateway);
    }
}
