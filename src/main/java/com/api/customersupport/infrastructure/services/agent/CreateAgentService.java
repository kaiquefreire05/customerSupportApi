package com.api.customersupport.infrastructure.services.agent;

import com.api.customersupport.application.gateway.agent.CreateAgentGateway;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.application.mapper.AgentMapper;
import com.api.customersupport.infrastructure.jpa.AgentEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Service
public class CreateAgentService implements CreateAgentGateway {
    // Dependency Injection
    private final AgentMapper agentMapper;
    private final AgentEntityRepository agentRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateAgentService(AgentMapper agentMapper, AgentEntityRepository agentRepository
            , PasswordEncoder passwordEncoder) {
        this.agentMapper = agentMapper;
        this.agentRepository = agentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Boolean createAgent(Agent agent) {
        try {
            log.info("Starting of agent creation::CreateAgentGatewayImpl");

            String encodedPassword = passwordEncoder.encode(agent.getPassword());
            agent.setPassword(encodedPassword);

            var userSaved = agentRepository.save(agentMapper.toEntity(agent));
            log.info("End of agent creation::CreateAgentGatewayImpl");
            return true;
        } catch (Exception ex) {
            log.error("Error in agent creation. Error details: {}::CreateAgentGatewayImpl", ex.getMessage());
            return false;
        }
    }
}
