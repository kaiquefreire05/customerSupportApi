package com.api.customersupport.infrastructure.services.client;

import com.api.customersupport.application.gateway.client.CreateClientGateway;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.mapper.ClientMapper;
import com.api.customersupport.infrastructure.repositories.ClientEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Service
public class CreateClientService implements CreateClientGateway {
    // Dependency Injection
    private final ClientMapper clientMapper;
    private final ClientEntityRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateClientService(ClientMapper clientMapper, ClientEntityRepository clientRepository
            , PasswordEncoder passwordEncoder) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Client createClient(Client client) {
        try {
            log.info("Starting of client creation::CreateClientGatewayImpl");

            String encodedPassword = passwordEncoder.encode(client.getPassword());
            client.setPassword(encodedPassword);

            var userSaved = clientRepository.save(clientMapper.toEntity(client));
            log.info("End of client creation::CreateClientGatewayImpl");
            return clientMapper.toDomainModel(userSaved);

        } catch (Exception ex) {
            log.error("Error in client creation. Error details: {}::CreateClientGatewayImpl", ex.getMessage());
            return null;
        }
    }
}
