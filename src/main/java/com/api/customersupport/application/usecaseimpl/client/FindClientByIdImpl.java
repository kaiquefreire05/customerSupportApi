package com.api.customersupport.application.usecaseimpl.client;

import com.api.customersupport.application.gateway.client.FindClientByIdGateway;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.usecases.client.FindClientByIdUseCase;

import java.util.UUID;

public class FindClientByIdImpl implements FindClientByIdUseCase {
    // Dependency Injection
    private final FindClientByIdGateway findClientByIdGateway;

    public FindClientByIdImpl(FindClientByIdGateway findClientByIdGateway) {
        this.findClientByIdGateway = findClientByIdGateway;
    }

    @Override
    public Client findClientById(UUID clientId) {
        return findClientByIdGateway.findClientById(clientId);
    }
}
