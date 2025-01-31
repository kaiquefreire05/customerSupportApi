package com.api.customersupport.infrastructure.adapters;

import com.api.customersupport.application.ports.output.AgentEmailAvailabilityPort;
import com.api.customersupport.application.ports.output.ClientEmailAvailabilityPort;
import com.api.customersupport.infrastructure.jpa.AgentEntityRepository;
import com.api.customersupport.infrastructure.jpa.ClientEntityRepository;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityAdapter implements AgentEmailAvailabilityPort, ClientEmailAvailabilityPort {
    // Dependency Injection
    private final AgentEntityRepository agentEntityRepository;
    private final ClientEntityRepository clientEntityRepository;

    public AvailabilityAdapter(AgentEntityRepository agentEntityRepository,
                               ClientEntityRepository clientEntityRepository) {
        this.agentEntityRepository = agentEntityRepository;
        this.clientEntityRepository = clientEntityRepository;
    }

    @Override
    public Boolean isAgentEmailAvailable(String email) {
        return agentEntityRepository.findByEmail(email).isEmpty();
    }

    @Override
    public Boolean isClientEmailAvailable(String email) {
        return clientEntityRepository.findByEmail(email).isEmpty();
    }
}
