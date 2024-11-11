package com.api.customersupport.infrastructure.controllers;

import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.infrastructure.dto.response.BaseResponse;
import com.api.customersupport.usecases.agent.AgentEmailAvailableUseCase;
import com.api.customersupport.usecases.agent.FindAgentByEmailUseCase;
import com.api.customersupport.usecases.agent.FindAgentByIdUseCase;
import com.api.customersupport.usecases.client.FindClientByIdUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@RestController
@RequestMapping("api/v1/agent")
public class AgentController {
    // Dependency injection
    private final AgentEmailAvailableUseCase agentEmailAvailableUseCase;
    private final FindAgentByEmailUseCase findAgentByEmailUseCase;
    private final FindAgentByIdUseCase findAgentById;

    public AgentController(AgentEmailAvailableUseCase agentEmailAvailableUseCase, FindAgentByEmailUseCase findAgentByEmailUseCase
            , FindAgentByIdUseCase findAgentById) {
        this.agentEmailAvailableUseCase = agentEmailAvailableUseCase;
        this.findAgentByEmailUseCase = findAgentByEmailUseCase;
        this.findAgentById = findAgentById;
    }


    /*// Controllers methods
    // TODO: Implement the logic for the below methods
    @GetMapping("uuid/{id}")
    public ResponseEntity<BaseResponse<Agent>> getById(@PathVariable UUID id) {
        log.info("Request received to get agent by id::AgentController");
        try {
            Agent agent = findAgentById.findAgentById(id);
            if (agent != null) {
                return null;
            } else {
                return null;
            }
        } catch(Exception ex) {
            log.error("Error occurred while trying to get agent by id. Error details: {}::AgentController", ex.getMessage());

        }
    }*/

}
