package com.api.customersupport.infrastructure.security;

import com.api.customersupport.application.gateway.agent.FindAgentByEmailGateway;
import com.api.customersupport.domain.exceptions.AgentNotFoundException;
import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.usecases.client.FindClientByEmailUseCase;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    // Dependency injection
    private final FindClientByEmailUseCase findClientByEmailUseCase;
    private final FindAgentByEmailGateway findAgentByEmailGateway;

    public CustomUserDetailService(FindClientByEmailUseCase findClientByEmailUseCase
            , FindAgentByEmailGateway findAgentByEmailGateway) {
        this.findClientByEmailUseCase = findClientByEmailUseCase;
        this.findAgentByEmailGateway = findAgentByEmailGateway;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Client client = findClientByEmailUseCase.findClientByEmail(email);
            if (client != null) {
                return User.builder()
                    .username(client.getEmail())
                    .password(client.getPassword())
                    .roles("CLIENT")
                    .build();
            }

            Agent agent = findAgentByEmailGateway.findAgentByEmail(email);
            if (agent != null) {
                return User.builder()
                    .username(agent.getEmail())
                    .password(agent.getPassword())
                    .roles("AGENT")
                    .build();
            }

            throw new UsernameNotFoundException("User not found");

        } catch (ClientNotFoundException | AgentNotFoundException ex) {
            throw new UsernameNotFoundException("Client or Agent not found.");
        }

    }

}
