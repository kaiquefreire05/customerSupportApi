package com.api.customersupport.usecases.agent;

import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.domain.models.Agent;

public interface CreateAgentUseCase {
    void createAgent(Agent agent) throws InternalServerErrorException;
}
