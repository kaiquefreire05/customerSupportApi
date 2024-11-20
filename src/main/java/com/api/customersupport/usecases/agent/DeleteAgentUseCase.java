package com.api.customersupport.usecases.agent;

import com.api.customersupport.domain.exceptions.InternalServerErrorException;

import java.util.UUID;

public interface DeleteAgentUseCase {
    void deleteAgent(UUID agentId) throws InternalServerErrorException;
}