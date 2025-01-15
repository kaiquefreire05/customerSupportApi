package com.api.customersupport.infrastructure.config;

import com.api.customersupport.application.gateway.client.*;
import com.api.customersupport.application.ports.input.clients.*;
import com.api.customersupport.application.services.client.*;
import com.api.customersupport.infrastructure.repositories.ClientEntityRepository;
import com.api.customersupport.infrastructure.services.client.ClientEmailAvailableService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public CreateClientUseCase createClientUseCase(CreateClientGateway createClientGateway) {
        return new CreateClientService(createClientGateway);
    }

    @Bean
    public FindClientByEmailUseCase findClientByEmailUseCase(FindClientByEmailGateway findClientByEmailGateway) {
        return new FindClientByEmailService(findClientByEmailGateway);
    }

    @Bean
    public ClientEmailAvailableGateway clientEmailAvailableGateway(ClientEntityRepository clientEntityRepository) {
        return new ClientEmailAvailableService(clientEntityRepository);
    }

    @Bean
    public FindClientByIdUseCase findClientByIdUseCase(FindClientByIdGateway findClientByIdGateway) {
        return new FindClientByIdService(findClientByIdGateway);
    }

    @Bean
    public ListClientsUseCase listClientsUseCase(ListClientGateway listClientGateway) {
        return new ListClientsService(listClientGateway);
    }

    @Bean
    public UpdateClientUseCase updateClientUseCase(UpdateClientGateway updateClientGateway) {
        return new UpdateClientService(updateClientGateway);
    }

    @Bean
    public DeleteClientUseCase deleteClientUseCase(DeleteClientGateway deleteClientGateway) {
        return new DeleteClientService(deleteClientGateway);
    }
}
