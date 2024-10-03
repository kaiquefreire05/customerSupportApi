package com.api.customersupport.application.usecaseimpl.client;

import com.api.customersupport.application.gateway.client.UpdateClientGateway;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.usecases.client.UpdateClientUseCase;

public class UpdateClientImpl implements UpdateClientUseCase {
    // Dependency Injection
    private final UpdateClientGateway updateClientGateway;

    public UpdateClientImpl(UpdateClientGateway updateClientGateway) {
        this.updateClientGateway = updateClientGateway;
    }

    @Override
    public Client updateClient(Client client) {
        return updateClientGateway.updateClient(client);
    }
}
