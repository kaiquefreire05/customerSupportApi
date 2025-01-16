package com.api.customersupport.infrastructure.services.client;

import com.api.customersupport.application.gateway.client.ListClientGateway;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.entities.ClientEntity;
import com.api.customersupport.application.mapper.ClientMapper;
import com.api.customersupport.infrastructure.jpa.ClientEntityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListClientService implements ListClientGateway {
    // Dependency Injection
    private final ClientEntityRepository clientEntityRepository;
    private final ClientMapper clientMapper;

    public ListClientService(ClientEntityRepository clientEntityRepository, ClientMapper clientMapper) {
        this.clientEntityRepository = clientEntityRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<Client> listClients() {
        List<ClientEntity> clientEntities = clientEntityRepository.findAll();
        return clientMapper.toDomainModelList(clientEntities);
    }
}
