package com.api.customersupport.infrastructure.security;

import com.api.customersupport.application.mapper.AgentMapper;
import com.api.customersupport.application.mapper.ClientMapper;
import com.api.customersupport.application.ports.input.agent.FindAgentByEmailUseCase;
import com.api.customersupport.application.ports.input.clients.FindClientByEmailUseCase;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.entities.AgentEntity;
import com.api.customersupport.infrastructure.entities.ClientEntity;
import com.api.customersupport.infrastructure.jpa.AgentEntityRepository;
import com.api.customersupport.infrastructure.jpa.ClientEntityRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    // Dependency injection
    private final AgentEntityRepository agentEntityRepository;
    private final ClientEntityRepository clientEntityRepository;
    private final AgentMapper agentMapper;
    private final ClientMapper clientMapper;

    public CustomUserDetailService(AgentEntityRepository agentEntityRepository,
                                   ClientEntityRepository clientEntityRepository, AgentMapper agentMapper,
                                   ClientMapper clientMapper) {
        this.agentEntityRepository = agentEntityRepository;
        this.clientEntityRepository = clientEntityRepository;
        this.agentMapper = agentMapper;
        this.clientMapper = clientMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            ClientEntity clientEntity = clientEntityRepository.findByEmail(email)
                    .orElseThrow(() -> new ClientNotFoundException(ErrorCodeEnum.ON0005.getCode(),
                        ErrorCodeEnum.ON0005.getMessage()));
            Client client = clientMapper.toDomainModel(clientEntity);
            return User.builder()
                    .username(client.getEmail())
                    .password(client.getPassword())
                    .roles("CLIENT")
                    .build();
        } catch (ClientNotFoundException e) {
            try {
                AgentEntity clientEntity = agentEntityRepository.findByEmail(email)
                        .orElseThrow(() -> new AgentNotFoundException(ErrorCodeEnum.ON0006.getCode(),
                        ErrorCodeEnum.ON0006.getMessage()));
                Agent agent = agentMapper.toDomainModel(clientEntity);
                return User.builder()
                        .username(agent.getEmail())
                        .password(agent.getPassword())
                        .roles("AGENT")
                        .build();
            } catch (AgentNotFoundException ex) {
                throw new UsernameNotFoundException("User not found with email: " + email);
            }
        }
    }

}
