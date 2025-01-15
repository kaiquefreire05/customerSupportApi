package com.api.customersupport.application.ports.input.agent;

import com.api.customersupport.domain.exceptions.InternalServerErrorException;

import java.util.UUID;

public interface DeleteAgentUseCase {
    void deleteAgent(UUID agentId) throws InternalServerErrorException;
}
