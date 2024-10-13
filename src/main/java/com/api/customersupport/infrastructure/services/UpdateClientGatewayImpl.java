package com.api.customersupport.infrastructure.services;

import com.api.customersupport.application.gateway.client.UpdateClientGateway;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.mapper.ClientMapper;
import com.api.customersupport.infrastructure.repositories.ClientEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateClientGatewayImpl implements UpdateClientGateway {
    // Dependency Injection
    private final ClientEntityRepository clientEntityRepository;
    private final ClientMapper clientMapper;

    public UpdateClientGatewayImpl(ClientEntityRepository clientEntityRepository, ClientMapper clientMapper) {
        this.clientEntityRepository = clientEntityRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client updateClient(Client client) {
       var clientEntity = clientMapper.toEntityUpdate(client);
       var updatedClientEntity = clientEntityRepository.save(clientEntity);
       return clientMapper.toDomainModel(updatedClientEntity);
    }
}
