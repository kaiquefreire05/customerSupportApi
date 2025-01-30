package com.api.customersupport.application.services.client;

import com.api.customersupport.application.ports.input.clients.FindClientByEmailUseCase;
import com.api.customersupport.application.ports.output.ClientRepositoryPort;
import com.api.customersupport.domain.models.Client;

public class FindClientByEmailService implements FindClientByEmailUseCase {
    // Dependency Injection
    private final ClientRepositoryPort repository;

    public FindClientByEmailService(ClientRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Client findClientByEmail(String email) {
        return repository.findClientByEmail(email);
    }
}
