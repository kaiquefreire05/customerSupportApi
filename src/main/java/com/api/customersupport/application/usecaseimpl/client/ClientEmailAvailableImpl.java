package com.api.customersupport.application.usecaseimpl.client;

import com.api.customersupport.application.gateway.client.ClientEmailAvailableGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.EmailUnavailableException;
import com.api.customersupport.usecases.client.ClientEmailAvailableUseCase;

public class ClientEmailAvailableImpl implements ClientEmailAvailableUseCase {
    // Dependency Injection
    private final ClientEmailAvailableGateway clientEmailAvailableGateway;

    public ClientEmailAvailableImpl(ClientEmailAvailableGateway clientEmailAvailableGateway) {
        this.clientEmailAvailableGateway = clientEmailAvailableGateway;
    }

    @Override
    public Boolean isEmailAvailable(String email) throws EmailUnavailableException {
        if (!clientEmailAvailableGateway.isEmailAvailable(email)) {
            throw new EmailUnavailableException(ErrorCodeEnum.ON0007.getCode(), ErrorCodeEnum.ON0007.getMessage());
        }
        return true;
    }
}
