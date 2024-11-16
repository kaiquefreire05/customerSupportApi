package com.api.customersupport.application.usecaseimpl.agent;

import com.api.customersupport.application.gateway.agent.DeleteAgentGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.usecases.agent.DeleteAgentUseCase;
import static com.api.customersupport.infrastructure.utils.Utils.log;

import java.util.UUID;

public class DeleteAgentImpl implements DeleteAgentUseCase {
    // Dependency Injection
    private final DeleteAgentGateway deleteAgentGateway;

    public DeleteAgentImpl(DeleteAgentGateway deleteAgentGateway) {
        this.deleteAgentGateway = deleteAgentGateway;
    }

    @Override
    public void deleteAgent(UUID agentId) throws InternalServerErrorException {
        log.info("Request received to delete agent with ID: {}", agentId);
        try {
            boolean success = deleteAgentGateway.deleteAgent(agentId);
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
