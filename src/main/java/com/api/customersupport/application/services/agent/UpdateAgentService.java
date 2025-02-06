package com.api.customersupport.application.services.agent;

import com.api.customersupport.application.mapper.AgentMapper;
import com.api.customersupport.application.ports.input.agent.FindAgentByIdUseCase;
import com.api.customersupport.application.ports.input.agent.UpdateAgentUseCase;
import com.api.customersupport.application.ports.output.availability.AgentEmailAvailabilityPort;
import com.api.customersupport.application.ports.output.AgentRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.EmailUnavailableException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.models.Agent;

import java.time.LocalDateTime;
import java.util.Objects;

public class UpdateAgentService implements UpdateAgentUseCase {
    // Dependency Injection
    private final AgentRepositoryPort agentRepositoryPort;
    private final AgentEmailAvailabilityPort agentEmailAvailabilityPort;
    private final FindAgentByIdUseCase findAgentByIdUseCase;
    private final AgentMapper agentMapper;

    public UpdateAgentService(AgentRepositoryPort agentRepositoryPort,
                              AgentEmailAvailabilityPort agentEmailAvailabilityPort,
                              FindAgentByIdUseCase findAgentByIdUseCase, AgentMapper agentMapper) {
        this.agentRepositoryPort = agentRepositoryPort;
        this.agentEmailAvailabilityPort = agentEmailAvailabilityPort;
        this.findAgentByIdUseCase = findAgentByIdUseCase;
        this.agentMapper = agentMapper;
    }

    @Override
    public Agent updateAgent(Agent agent) throws AgentNotFoundException, EmailUnavailableException,
            EmailInvalidException, PhoneInvalidException {
        Agent existentUser = findAgentByIdUseCase.findAgentById(agent.getId());
        if (!Objects.equals(agent.getEmail(), existentUser.getEmail())) {
            checkEmailAvailability(agent.getEmail());
        }
        agentMapper.updateValues(agent, existentUser);
        existentUser.setUpdatedAt(LocalDateTime.now());
        return agentRepositoryPort.updateAgent(agent);
    }

    // Helper Methods
    public void checkEmailAvailability(String email) throws EmailUnavailableException {
        if (!agentEmailAvailabilityPort.isAgentEmailAvailable(email)) {
           throw new EmailUnavailableException(ErrorCodeEnum.ON0007.getCode(), ErrorCodeEnum.ON0007.getMessage());
        }
    }
}
