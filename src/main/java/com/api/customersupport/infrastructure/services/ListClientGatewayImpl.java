package com.api.customersupport.infrastructure.services;

import com.api.customersupport.application.gateway.client.ListClientGateway;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.entities.ClientEntity;
import com.api.customersupport.infrastructure.mapper.ClientMapper;
import com.api.customersupport.infrastructure.repositories.ClientEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListClientGatewayImpl implements ListClientGateway {
    // Dependency Injection
    private final ClientEntityRepository clientEntityRepository;
    private final ClientMapper clientMapper;

    public ListClientGatewayImpl(ClientEntityRepository clientEntityRepository, ClientMapper clientMapper) {
        this.clientEntityRepository = clientEntityRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<Client> listClients() {
        List<ClientEntity> clientEntities = clientEntityRepository.findAll();
        return clientMapper.toDomainModelList(clientEntities);
    }
}
