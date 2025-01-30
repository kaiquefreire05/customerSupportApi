package com.api.customersupport.infrastructure.adapters;

import com.api.customersupport.application.mapper.AgentMapper;
import com.api.customersupport.application.ports.output.AgentRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.infrastructure.entities.AgentEntity;
import com.api.customersupport.infrastructure.jpa.AgentEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class AgentRepositoryAdapter implements AgentRepositoryPort {
    // Dependency Injection
    private final AgentEntityRepository repository;
    private final AgentMapper mapper;
    private final PasswordEncoder encoder;

    public AgentRepositoryAdapter(AgentEntityRepository repository, AgentMapper mapper, PasswordEncoder encoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public Agent saveAgent(Agent agent) {
        try {
            log.info("Starting of agent creation::AgentRepositoryAdapter");
            var encodedPassword = encoder.encode(agent.getPassword());
            agent.setPassword(encodedPassword);
            var agentSaved = repository.save(mapper.toEntity(agent));
            log.info("End of agent creation::AgentRepositoryAdapter");
            return mapper.toDomainModel(agentSaved);
        } catch (Exception ex) {
            log.error("Error in agent creation. Error details: {}::AgentRepositoryAdapter", ex.getMessage());
            return null;
        }
    }

    @Override
    public Agent updateAgent(Agent agent) {
        try {
            log.info("Starting of agent update with id: {}::AgentRepositoryAdapter", agent.getId());
            var existentAgent = repository.findById(agent.getId())
                    .orElseThrow(() -> new AgentNotFoundException(ErrorCodeEnum.ON0006.getCode(),
                            ErrorCodeEnum.ON0006.getMessage()));
            var updatedAgent = mapper.toEntityUpdate(agent);
            updatedAgent.setPassword(encoder.encode(agent.getPassword()));
            updatedAgent.setUpdatedAt(LocalDateTime.now());

            var savedAgent = repository.save(updatedAgent);
            log.info("End of agent update with id: {}::AgentRepositoryAdapter", agent.getId());
            return mapper.toDomainModel(savedAgent);

        } catch (Exception ex) {
            log.error("Error in agent update. Error details: {}::AgentRepositoryAdapter", ex.getMessage());
            return null;
        }
    }

    @Override
    public Agent getAgentById(UUID id) throws AgentNotFoundException {
        log.info("Finding agent by id: {}::AgentRepositoryAdapter", id);
        return repository.findById(id).map(mapper::toDomainModel)
                .orElseThrow(() -> new AgentNotFoundException(ErrorCodeEnum.ON0006.getCode(),
                        ErrorCodeEnum.ON0006.getMessage()));
    }

    @Override
    public Agent getAgentByEmail(String email) throws AgentNotFoundException {
        log.info("Finding agent by email: {}::AgentRepositoryAdapter", email);
        return repository.findByEmail(email).map(mapper::toDomainModel)
                .orElseThrow(() -> new AgentNotFoundException(ErrorCodeEnum.ON0006.getCode(),
                        ErrorCodeEnum.ON0006.getMessage()));
    }

    @Override
    public List<Agent> listAgents() {
        List<AgentEntity> agentEntities = repository.findAll();
        return mapper.toDomainModelList(agentEntities);
    }

    @Override
    public Boolean deleteAgent(UUID id) {
        try {
            log.info("Deleting agent with id: {}::AgentRepositoryAdapter", id);
            repository.deleteById(id);
            log.info("Agent with id: {} deleted successfully::AgentRepositoryAdapter", id);
            return true;
        } catch (IllegalArgumentException ex) {
            log.error("Invalid agent ID: {}. Error details: {}::AgentRepositoryAdapter", id, ex.getMessage());
            return false;
        } catch (Exception ex) {
            log.error("Failed to delete agent with id: {}. Error details: {}::AgentRepositoryAdapter", id, ex.getMessage());
            return false;
        }
    }
}
