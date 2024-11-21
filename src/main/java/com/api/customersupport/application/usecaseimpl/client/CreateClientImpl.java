package com.api.customersupport.application.usecaseimpl.client;

import com.api.customersupport.application.gateway.client.CreateClientGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.usecases.client.CreateClientUseCase;

public class CreateClientImpl implements CreateClientUseCase {
    // Dependency Injection
    private final CreateClientGateway createClientGateway;

    public CreateClientImpl(CreateClientGateway createClientGateway) {
        this.createClientGateway = createClientGateway;
    }

    @Override
    public Client createClient(Client client) throws InternalServerErrorException {
        Client createdClient = createClientGateway.createClient(client);
        if (createdClient == null) {
            throw new InternalServerErrorException(ErrorCodeEnum.ON0003.getCode(), ErrorCodeEnum.ON0003.getMessage());
        }
        return createdClient;
    }
}
