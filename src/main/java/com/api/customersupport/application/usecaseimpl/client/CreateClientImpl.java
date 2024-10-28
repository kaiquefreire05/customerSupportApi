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
    public void createClient(Client client) throws InternalServerErrorException {
        boolean success = createClientGateway.createClient(client);
        if (!success) {
            throw new InternalServerErrorException(ErrorCodeEnum.ON0003.getCode(), ErrorCodeEnum.ON0003.getMessage());
        }
    }
}
