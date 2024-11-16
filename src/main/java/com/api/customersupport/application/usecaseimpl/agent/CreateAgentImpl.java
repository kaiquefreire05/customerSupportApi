package com.api.customersupport.application.usecaseimpl.agent;

import com.api.customersupport.application.gateway.agent.CreateAgentGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.usecases.agent.CreateAgentUseCase;

public class CreateAgentImpl implements CreateAgentUseCase {
    // Dependency Injection
    private final CreateAgentGateway createAgentGateway;

    public CreateAgentImpl(CreateAgentGateway createAgentGateway) {
        this.createAgentGateway = createAgentGateway;
    }

    @Override
    public void createAgent(Agent agent) throws InternalServerErrorException {
        boolean success = createAgentGateway.createAgent(agent);
        if (!success) {
            throw new InternalServerErrorException(ErrorCodeEnum.ON0009.getCode(), ErrorCodeEnum.ON0009.getMessage());
        }
    }
}
