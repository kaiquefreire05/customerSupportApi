package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.client.CreateClientGateway;
import com.api.customersupport.application.gateway.client.FindClientByEmailGateway;
import com.api.customersupport.application.usecaseimpl.client.CreateClientImpl;
import com.api.customersupport.application.usecaseimpl.client.FindClientByEmailImpl;
import com.api.customersupport.usecases.client.CreateClientUseCase;
import com.api.customersupport.usecases.client.FindClientByEmailUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public CreateClientUseCase createClientUseCase(CreateClientGateway createClientGateway) {
        return new CreateClientImpl(createClientGateway);
    }

    @Bean
    public FindClientByEmailUseCase findClientByEmailUseCase(FindClientByEmailGateway findClientByEmailGateway) {
        return new FindClientByEmailImpl(findClientByEmailGateway);
    }
}
