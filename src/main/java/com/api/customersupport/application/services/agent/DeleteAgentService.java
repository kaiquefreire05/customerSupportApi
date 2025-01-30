package com.api.customersupport.application.services.agent;

import com.api.customersupport.application.ports.input.agent.DeleteAgentUseCase;
import com.api.customersupport.application.ports.output.AgentRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;

import java.util.UUID;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class DeleteAgentService implements DeleteAgentUseCase {
    // Dependency Injection
    private final AgentRepositoryPort agentRepositoryPort;

    public DeleteAgentService(AgentRepositoryPort agentRepositoryPort) {
        this.agentRepositoryPort = agentRepositoryPort;
    }

    @Override
    public void deleteAgent(UUID agentId) throws InternalServerErrorException {
        log.info("Request received to delete agent with ID: {}", agentId);
        try {
            boolean success = agentRepositoryPort.deleteAgent(agentId);
            if (!success) {
                throw new InternalServerErrorException(ErrorCodeEnum.ON0008.getCode(), ErrorCodeEnum.ON0008.getMessage());
            }
        } catch (Exception ex) {
            log.info("Error deleting agent with ID: {}. Error details: {}", agentId, ex.getMessage());
            throw new InternalServerErrorException(ErrorCodeEnum.ON0008.getCode()
                    , ErrorCodeEnum.concatError(ex.getMessage(), ErrorCodeEnum.ON0008));
        }
    }
}
