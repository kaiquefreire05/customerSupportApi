package com.api.customersupport.infrastructure.services.client;

import com.api.customersupport.application.gateway.client.UpdateClientGateway;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.application.mapper.ClientMapper;
import com.api.customersupport.infrastructure.repositories.ClientEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateClientService implements UpdateClientGateway {
    // Dependency Injection
    private final ClientEntityRepository clientEntityRepository;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;

    public UpdateClientService(ClientEntityRepository clientEntityRepository, ClientMapper clientMapper
            , PasswordEncoder passwordEncoder) {
        this.clientEntityRepository = clientEntityRepository;
        this.clientMapper = clientMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public Client updateClient(Client client) {
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        var clientEntity = clientMapper.toEntityUpdate(client);
        var updatedClientEntity = clientEntityRepository.save(clientEntity);
        return clientMapper.toDomainModel(updatedClientEntity);
    }
}
