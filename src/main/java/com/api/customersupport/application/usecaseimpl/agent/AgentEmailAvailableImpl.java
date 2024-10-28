package com.api.customersupport.application.usecaseimpl.agent;

import com.api.customersupport.application.gateway.agent.AgentEmailAvailableGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.EmailUnavailableException;
import com.api.customersupport.usecases.agent.AgentEmailAvailableUseCase;

public class AgentEmailAvailableImpl implements AgentEmailAvailableUseCase {
    // Dependency Injection
    private final AgentEmailAvailableGateway agentEmailAvailableGateway;

    public AgentEmailAvailableImpl(AgentEmailAvailableGateway agentEmailAvailableGateway) {
        this.agentEmailAvailableGateway = agentEmailAvailableGateway;
    }

    @Override
    public Boolean isEmailAvailable(String email) throws EmailUnavailableException {
        if (!agentEmailAvailableGateway.isEmailAvailable(email)) {
            throw new EmailUnavailableException(ErrorCodeEnum.ON0007.getCode(), ErrorCodeEnum.ON0007.getMessage());
        }
        return true;
    }
}
