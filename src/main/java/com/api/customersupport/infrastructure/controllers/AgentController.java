package com.api.customersupport.infrastructure.controllers;

import com.api.customersupport.application.mapper.AgentMapper;
import com.api.customersupport.application.ports.input.agent.*;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.infrastructure.dto.requests.agent.CreateAgentRequest;
import com.api.customersupport.infrastructure.dto.requests.agent.UpdateAgentRequest;
import com.api.customersupport.infrastructure.dto.response.BaseResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@RestController
@RequestMapping("api/v1/agent")
@CrossOrigin(origins = "http://localhost:3000")
public class AgentController {
    // Dependency injection
    private final AgentEmailAvailableUseCase agentEmailAvailableUseCase;
    private final FindAgentByEmailUseCase findAgentByEmailUseCase;
    private final FindAgentByIdUseCase findAgentById;
    private final ListAgentsUseCase listAgentsUseCase;
    private final DeleteAgentUseCase deleteAgentUseCase;
    private final CreateAgentUseCase createAgentUseCase;
    private final UpdateAgentUseCase updateAgentUseCase;
    private final AgentMapper agentMapper;

    public AgentController(AgentEmailAvailableUseCase agentEmailAvailableUseCase
            , FindAgentByEmailUseCase findAgentByEmailUseCase, FindAgentByIdUseCase findAgentById
            , ListAgentsUseCase listAgentsUseCase, DeleteAgentUseCase deleteAgentUseCase
            , CreateAgentUseCase createAgentUseCase, UpdateAgentUseCase updateAgentUseCase, AgentMapper agentMapper) {
        this.agentEmailAvailableUseCase = agentEmailAvailableUseCase;
        this.findAgentByEmailUseCase = findAgentByEmailUseCase;
        this.findAgentById = findAgentById;
        this.listAgentsUseCase = listAgentsUseCase;
        this.deleteAgentUseCase = deleteAgentUseCase;
        this.createAgentUseCase = createAgentUseCase;
        this.updateAgentUseCase = updateAgentUseCase;
        this.agentMapper = agentMapper;
    }

    // Controllers methods
    @GetMapping("/all")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<List<Agent>>> getAll() {
        log.info("Request received to get all entities agent::AgentController");
        try {
            List<Agent> agents = listAgentsUseCase.listAgents();
            return ResponseEntity.ok().body(BaseResponse.<List<Agent>>builder().success(true)
                    .message("All agents successfully returned.").result(agents).build());

        } catch (Exception ex) {
            log.error("Error occurred while trying to get all agents. Error details: {}::AgentController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<List<Agent>>builder().success(false)
                    .message("Error while trying to get all agents").build());
        }
    }

    @GetMapping("/uuid/{uuid}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<Agent>> getById(@PathVariable UUID uuid) {
        log.info("Request received to get a entity by UUID:AgentController");
        try {
            log.info("Starting to get a entity by UUID:AgentController");
            Agent agent = findAgentById.findAgentById(uuid);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<Agent>builder().success(true)
                    .result(agent).message("Agent with id " + uuid + " successfully returned").build());
        } catch (AgentNotFoundException ex) {
            log.error("Agent with id {} not found::AgentController", uuid);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.<Agent>builder().success(false)
                    .result(null).message("Agent with id " + uuid + " not found").build());

        } catch (Exception ex) {
            log.error("Error occurred while trying to get agent by id. Error details: {}::AgentController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<Agent>builder().success(false)
                    .result(null).message("Error while trying to get agent by id").build());
        }
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<Agent>> getByEmail(@PathVariable String email) {
        log.info("Request received to get a entity by email:AgentController");
        try {
            log.info("Starting to get a entity by email:AgentController");
            Agent agent = findAgentByEmailUseCase.findAgentByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<Agent>builder().success(true)
                    .result(agent).message("Agent with email " + email + " successfully returned").build());
        } catch (AgentNotFoundException ex) {
            log.warn("Agent with email {} not found::AgentController", email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.<Agent>builder().success(false)
                    .result(null).message("Agent with email " + email + " not found").build());

        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<Agent>builder().success(false)
                    .result(null).message("Error while trying to get agent by email").build());
        }
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<Boolean>> deleteAgent(@PathVariable UUID uuid) {
        log.info("Request received to delete a entity by UUID:AgentController");
        try {
            log.info("Starting to delete a entity by UUID:AgentController");
            deleteAgentUseCase.deleteAgent(uuid);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<Boolean>builder().success(true)
                    .result(true).message("Agent with id " + uuid + " successfully deleted").build());
        } catch (Exception ex) {
            log.error("Error occurred while trying to delete agent by id. Error details: {}::AgentController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<Boolean>builder().success(false)
                    .result(false).message("Error while trying to delete agent by id").build());
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<String>> createAgent(@Valid @RequestBody CreateAgentRequest request) {
        log.info("Request received to create a entity agent::AgentController");
        try {
            log.info("Checking if email is available::AgentController");
            agentEmailAvailableUseCase.isEmailAvailable(request.email());
            log.info("Email is available::AgentController");
            createAgentUseCase.createAgent(agentMapper.toDomainCreateRequest(request));
            log.info("Agent created successfully::AgentController");
            return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.<String>builder().success(true)
                    .message("Agent created successfully.").build());

        } catch (Exception ex) {
            log.error("Error occurred while creating agent. Error details: {}::AgentController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<String>builder()
                    .success(false).message("Error occurred while creating a agent.").build());
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<String>> updateAgent(@Valid @RequestBody UpdateAgentRequest request) {
        log.info("Request received to update agent:AgentController");
        try {
            Agent foundAgent = findAgentById.findAgentById(request.id());
            log.info("Updating agent details:AgentController");
            foundAgent.setName(request.name());
            foundAgent.setEmail(request.email());
            foundAgent.setPhone(request.phone());
            foundAgent.setPassword(request.password());
            foundAgent.setUpdatedAt(LocalDateTime.now());

            updateAgentUseCase.updateAgent(foundAgent);
            log.info("Agent updated successfully:AgentController");
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<String>builder().success(true)
                    .message("Agent updated successfully.").build());

        } catch (Exception ex) {
            log.info("Error occurred while updating agent. Error details: {}::AgentController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<String>builder()
                    .success(false).message("Error occurred while updating agent.").build());
        }
    }
}
