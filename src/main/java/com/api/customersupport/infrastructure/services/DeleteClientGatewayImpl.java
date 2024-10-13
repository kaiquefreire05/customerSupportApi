package com.api.customersupport.infrastructure.services;

import com.api.customersupport.application.gateway.client.DeleteClientGateway;
import com.api.customersupport.infrastructure.repositories.ClientEntityRepository;
import org.springframework.stereotype.Service;

import static com.api.customersupport.infrastructure.utils.Utils.log;

import java.util.UUID;

@Service
public class DeleteClientGatewayImpl implements DeleteClientGateway {
    // Dependency Injection
    private final ClientEntityRepository clientEntityRepository;

    public DeleteClientGatewayImpl(ClientEntityRepository clientEntityRepository) {
        this.clientEntityRepository = clientEntityRepository;
    }

    @Override
    public boolean deleteClient(UUID clientId) {
        try {
            log.info("Deleting client with id: {}", clientId);
            clientEntityRepository.deleteById(clientId);
            log.info("Client with id: {} deleted successfully", clientId);
            return true;

        } catch (IllegalArgumentException ex) {
            log.error("Invalid client ID: {}. Error details: {}", clientId, ex.getMessage());
            return false;

        } catch (Exception ex) {
            log.error("Failed to delete client with id: {}. Error details: {}", clientId, ex.getMessage());
            return false;
        }
    }

}
