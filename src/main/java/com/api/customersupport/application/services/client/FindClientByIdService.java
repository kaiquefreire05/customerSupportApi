package com.api.customersupport.application.services.client;

import com.api.customersupport.application.gateway.client.FindClientByIdGateway;
import com.api.customersupport.application.ports.output.ClientRepositoryPort;
import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.application.ports.input.clients.FindClientByIdUseCase;

import java.util.UUID;

public class FindClientByIdService implements FindClientByIdUseCase {
    // Dependency Injection
    private final ClientRepositoryPort clientRepositoryPort;

    public FindClientByIdService(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;
    }

    @Override
    public Client findClientById(UUID clientId) throws ClientNotFoundException {
        return clientRepositoryPort.getClientById(clientId);
    }
}
