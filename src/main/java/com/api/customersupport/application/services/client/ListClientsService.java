package com.api.customersupport.application.services.client;

import com.api.customersupport.application.gateway.client.ListClientGateway;
import com.api.customersupport.application.ports.output.ClientRepositoryPort;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.application.ports.input.clients.ListClientsUseCase;

import java.util.List;

public class ListClientsService implements ListClientsUseCase {
    // Dependency Injection
    private final ClientRepositoryPort repository;

    public ListClientsService(ClientRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Client> listClients() {
        return repository.listClients();

    }

}
