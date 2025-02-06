package com.api.customersupport.application.services.client;

import com.api.customersupport.application.mapper.ClientMapper;
import com.api.customersupport.application.ports.input.clients.FindClientByIdUseCase;
import com.api.customersupport.application.ports.input.clients.UpdateClientUseCase;
import com.api.customersupport.application.ports.output.availability.ClientEmailAvailabilityPort;
import com.api.customersupport.application.ports.output.ClientRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.exceptions.EmailInvalidException;
import com.api.customersupport.domain.exceptions.EmailUnavailableException;
import com.api.customersupport.domain.exceptions.PhoneInvalidException;
import com.api.customersupport.domain.models.Client;

import java.time.LocalDateTime;
import java.util.Objects;

public class UpdateClientService implements UpdateClientUseCase {
    // Dependency Injection
    private final ClientRepositoryPort repository;
    private final FindClientByIdUseCase findClientByIdUseCase;
    private final ClientMapper clientMapper;
    private final ClientEmailAvailabilityPort clientEmailAvailabilityPort;

    public UpdateClientService(ClientRepositoryPort repository, FindClientByIdUseCase findClientByIdUseCase, ClientMapper clientMapper, ClientEmailAvailabilityPort clientEmailAvailabilityPort) {
        this.repository = repository;
        this.findClientByIdUseCase = findClientByIdUseCase;
        this.clientMapper = clientMapper;
        this.clientEmailAvailabilityPort = clientEmailAvailabilityPort;
    }

    @Override
    public Client updateClient(Client client) throws ClientNotFoundException, EmailUnavailableException,
            EmailInvalidException, PhoneInvalidException {
        Client existentClient = findClientByIdUseCase.findClientById(client.getId());
        if (!Objects.equals(client.getEmail(), existentClient.getEmail())) {
            checkEmailAvailability(client.getEmail());
        }
        clientMapper.updateValues(client, existentClient);
        existentClient.setUpdatedAt(LocalDateTime.now());
        return repository.updateClient(client);
    }

    // Helper Methods
    public void checkEmailAvailability(String email) throws EmailUnavailableException {
        if (!clientEmailAvailabilityPort.isClientEmailAvailable(email)) {
           throw new EmailUnavailableException(ErrorCodeEnum.ON0007.getCode(), ErrorCodeEnum.ON0007.getMessage());
        }
    }
}
