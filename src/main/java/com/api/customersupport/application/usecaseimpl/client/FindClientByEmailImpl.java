package com.api.customersupport.application.usecaseimpl.client;

import com.api.customersupport.application.gateway.client.FindClientByEmailGateway;
import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.usecases.client.FindClientByEmailUseCase;

public class FindClientByEmailImpl implements FindClientByEmailUseCase {
    // Dependency Injection
    private final FindClientByEmailGateway findClientByEmailGateway;

    public FindClientByEmailImpl(FindClientByEmailGateway findClientByEmailGateway) {
        this.findClientByEmailGateway = findClientByEmailGateway;
    }

    @Override
    public Client findClientByEmail(String email) throws ClientNotFoundException {
        return findClientByEmailGateway.findClientByEmail(email);
    }
}
