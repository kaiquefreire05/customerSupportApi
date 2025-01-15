package com.api.customersupport.application.services.client;

import com.api.customersupport.application.gateway.client.CreateClientGateway;
import com.api.customersupport.application.ports.output.ClientEmailAvailabilityPort;
import com.api.customersupport.application.ports.output.ClientRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.EmailUnavailableException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.application.ports.input.clients.CreateClientUseCase;

public class CreateClientService implements CreateClientUseCase {
    // Dependency Injection
    private final ClientRepositoryPort clientRepositoryPort;
    private final ClientEmailAvailabilityPort clientEmailAvailabilityPort;

    public CreateClientService(ClientRepositoryPort clientRepositoryPort,
                               ClientEmailAvailabilityPort clientEmailAvailabilityPort) {
        this.clientRepositoryPort = clientRepositoryPort;
        this.clientEmailAvailabilityPort = clientEmailAvailabilityPort;
    }

    @Override
    public Client createClient(Client client) throws EmailUnavailableException {
        checkEmailAvailability(client.getEmail());
        return clientRepositoryPort.createClient(client);
    }

    // Helper Methods
    public void checkEmailAvailability(String email) throws EmailUnavailableException {
        if (!clientEmailAvailabilityPort.isEmailAvailable(email)) {
           throw new EmailUnavailableException(ErrorCodeEnum.ON0007.getCode(), ErrorCodeEnum.ON0007.getMessage());
        }
    }
}
