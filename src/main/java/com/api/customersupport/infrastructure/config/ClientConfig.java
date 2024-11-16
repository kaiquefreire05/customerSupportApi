package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.client.*;
import com.api.customersupport.application.usecaseimpl.client.*;
import com.api.customersupport.infrastructure.mapper.ClientMapper;
import com.api.customersupport.infrastructure.repositories.ClientEntityRepository;
import com.api.customersupport.infrastructure.services.client.ClientEmailAvailableGatewayImpl;
import com.api.customersupport.usecases.client.*;
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
