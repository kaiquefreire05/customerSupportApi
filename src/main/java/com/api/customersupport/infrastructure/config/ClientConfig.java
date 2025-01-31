package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.ports.input.clients.FindClientByEmailUseCase;
import com.api.customersupport.application.ports.output.ClientRepositoryPort;
import com.api.customersupport.application.services.client.FindClientByEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public FindClientByEmailUseCase findClientByEmailUseCase(ClientRepositoryPort repository) {
        return new FindClientByEmailService(repository);
    }
}
