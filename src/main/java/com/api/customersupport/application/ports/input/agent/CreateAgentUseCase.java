package com.api.customersupport.application.ports.input.agent;

import com.api.customersupport.domain.exceptions.EmailUnavailableException;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.domain.models.Agent;

public interface CreateAgentUseCase {
    void createAgent(Agent agent) throws InternalServerErrorException, EmailUnavailableException;
}
