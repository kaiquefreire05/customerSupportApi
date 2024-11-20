package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.client.*;
import com.api.customersupport.application.usecaseimpl.client.*;
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

    @Bean
    public ClientEmailAvailableGateway clientEmailAvailableGateway(ClientEntityRepository clientEntityRepository) {
        return new ClientEmailAvailableGatewayImpl(clientEntityRepository);
    }

    @Bean
    public FindClientByIdUseCase findClientByIdUseCase(FindClientByIdGateway findClientByIdGateway) {
        return new FindClientByIdImpl(findClientByIdGateway);
    }

    @Bean
    public ListClientsUseCase listClientsUseCase(ListClientGateway listClientGateway) {
        return new ListClientsImpl(listClientGateway);
    }

    @Bean
    public UpdateClientUseCase updateClientUseCase(UpdateClientGateway updateClientGateway) {
        return new UpdateClientImpl(updateClientGateway);
    }

    @Bean
    public DeleteClientUseCase deleteClientUseCase(DeleteClientGateway deleteClientGateway) {
        return new DeleteClientImpl(deleteClientGateway);
    }
}
