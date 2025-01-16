package com.api.customersupport.infrastructure.services.client;

import com.api.customersupport.application.gateway.client.FindClientByEmailGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.application.mapper.ClientMapper;
import com.api.customersupport.infrastructure.jpa.ClientEntityRepository;
import org.springframework.stereotype.Service;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Service
public class FindClientByEmailService implements FindClientByEmailGateway {
    // Dependency Injection
    private final ClientEntityRepository clientRepository;
    private final ClientMapper clientMapper;

    public FindClientByEmailService(ClientEntityRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client findClientByEmail(String email) throws ClientNotFoundException {
        log.info("Finding client by email: {}::FindClientByEmailGatewayImpl", email);
        return clientRepository.findByEmail(email)
                .map(clientMapper::toDomainModel)
                .orElseThrow(() -> new ClientNotFoundException(ErrorCodeEnum.ON0005.getCode()
                        , ErrorCodeEnum.ON0005.getMessage()));
    }
}
