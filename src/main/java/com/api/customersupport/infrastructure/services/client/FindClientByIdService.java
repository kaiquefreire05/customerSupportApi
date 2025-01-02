package com.api.customersupport.infrastructure.services.client;

import com.api.customersupport.application.gateway.client.FindClientByIdGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.mapper.ClientMapper;
import com.api.customersupport.infrastructure.repositories.ClientEntityRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Service
public class FindClientByIdService implements FindClientByIdGateway {
    // Dependency Injection
    private final ClientEntityRepository clientRepository;
    private final ClientMapper clientMapper;

    public FindClientByIdService(ClientEntityRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client findClientById(UUID clientId) throws ClientNotFoundException {
        log.info("Searching client by id: {}::FindClientByIdGatewayImpl", clientId);
        return clientRepository.findById(clientId)
                .map(clientMapper::toDomainModel)
                .orElseThrow(() -> new ClientNotFoundException(ErrorCodeEnum.ON0005.getCode()
                        , ErrorCodeEnum.ON0005.getMessage()));
    }
}
