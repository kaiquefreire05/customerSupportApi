package com.api.customersupport.infrastructure.services;

import com.api.customersupport.application.gateway.client.CreateClientGateway;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.mapper.ClientMapper;
import com.api.customersupport.infrastructure.repositories.ClientEntityRepository;
import org.springframework.stereotype.Service;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Service
public class CreateClientGatewayImpl implements CreateClientGateway {
    // Dependency Injection
    private final ClientMapper clientMapper;
    private final ClientEntityRepository clientRepository;

    public CreateClientGatewayImpl(ClientMapper clientMapper, ClientEntityRepository clientRepository) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public Boolean createClient(Client client) {
        try {
            log.info("Starting of client creation::CreateClientGatewayImpl");
            var userSaved = clientRepository.save(clientMapper.toEntity(client));
            log.info("End of client creation::CreateClientGatewayImpl");
            return true;

        } catch (Exception ex) {
            log.error("Error in client creation. Error details: {}::CreateClientGatewayImpl", ex.getMessage());
            return false;
        }
    }
}
