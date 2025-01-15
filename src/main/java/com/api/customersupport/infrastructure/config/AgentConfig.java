package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.agent.*;
import com.api.customersupport.application.gateway.client.ClientEmailAvailableGateway;
import com.api.customersupport.application.ports.input.agent.*;
import com.api.customersupport.application.services.agent.*;
import com.api.customersupport.application.mapper.AgentMapper;
import com.api.customersupport.infrastructure.repositories.AgentEntityRepository;
import com.api.customersupport.infrastructure.services.agent.AgentEmailAvailableService;
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
        return new FindAgentByEmailService(findAgentByEmailGateway);
    }

    @Bean
    public FindAgentByIdUseCase findAgentByIdUseCase(FindAgentByIdGateway findAgentByIdGateway) {
        return new FindAgentByIdService(findAgentByIdGateway);
    }

    @Bean
    public ClientEmailAvailableUseCase clientEmailAvailableUseCase(ClientEmailAvailableGateway clientEmailAvailableGateway) {
        return new ClientEmailAvailableImpl(clientEmailAvailableGateway);
    }

    @Bean
    public ListAgentsUseCase listAgentsUseCase(ListAgentsGateway listAgentsGateway) {
        return new ListAgentsService(listAgentsGateway);
    }

    @Bean
    public ListAgentsGateway listAgentsGateway(AgentEntityRepository agentEntityRepository, AgentMapper agentMapper) {
        return new com.api.customersupport.infrastructure.services.agent.ListAgentsService(agentEntityRepository, agentMapper);
    }

    @Bean
    public DeleteAgentUseCase deleteAgentUseCase(DeleteAgentGateway deleteAgentGateway) {
        return new DeleteAgentService(deleteAgentGateway);
    }

    @Bean
    public DeleteAgentGateway deleteAgentGateway(AgentEntityRepository agentEntityRepository) {
        return new com.api.customersupport.infrastructure.services.agent.DeleteAgentService(agentEntityRepository);
    }

    @Bean
    public CreateAgentUseCase createAgentUseCase(CreateAgentGateway createAgentGateway) {
        return new CreateAgentService(createAgentGateway);
    }

    @Bean
    public UpdateAgentUseCase updateAgentUseCase(UpdateAgentGateway updateAgentGateway) {
        return new UpdateAgentService(updateAgentGateway);
    }
}
