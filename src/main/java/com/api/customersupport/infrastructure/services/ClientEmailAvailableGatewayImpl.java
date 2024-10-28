package com.api.customersupport.infrastructure.services;

import com.api.customersupport.application.gateway.client.ClientEmailAvailableGateway;
import com.api.customersupport.infrastructure.repositories.ClientEntityRepository;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class ClientEmailAvailableGatewayImpl implements ClientEmailAvailableGateway {
    // Dependency Injection
    private final ClientEntityRepository clientEntityRepository;

    public ClientEmailAvailableGatewayImpl(ClientEntityRepository clientEntityRepository) {
        this.clientEntityRepository = clientEntityRepository;
    }

    @Override
    public Boolean isEmailAvailable(String email) {
        log.info("Start of isEmailAvailable method::ClientEmailAvailableGatewayImpl");
        return !clientEntityRepository.existsByEmail(email);
    }
}
