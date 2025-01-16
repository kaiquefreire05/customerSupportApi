package com.api.customersupport.infrastructure.adapters;

import com.api.customersupport.application.mapper.ClientMapper;
import com.api.customersupport.application.ports.output.ClientRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.entities.ClientEntity;
import com.api.customersupport.infrastructure.jpa.ClientEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.UUID;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class ClientRepositoryAdapter implements ClientRepositoryPort {
    // Dependency Injection
    private final ClientEntityRepository repository;
    private final ClientMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public ClientRepositoryAdapter(ClientEntityRepository repository, ClientMapper mapper,
                                   PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Client createClient(Client client) {
        try {
            log.info("Starting of client creation::ClientRepositoryAdapter");
            var encodedPassword = passwordEncoder.encode(client.getPassword());
            client.setPassword(encodedPassword);
            var clientSaved = repository.save(mapper.toEntity(client));
            log.info("End of client creation::ClientRepositoryAdapter");
            return mapper.toDomainModel(clientSaved);
        } catch (RuntimeException ex) {
            log.error("Error in client creation. Error details: {}::ClientRepositoryAdapter", ex.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteClient(UUID clientId) {
        try {
            log.info("Deleting client with id: {}::ClientRepositoryAdapter", clientId);
            repository.deleteById(clientId);
            log.info("Client with id: {} deleted successfully::ClientRepositoryAdapter", clientId);
            return true;
        } catch (IllegalArgumentException ex) {
            log.error("Invalid client ID: {}. Error details: {}::ClientRepositoryAdapter", clientId, ex.getMessage());
            return false;

        } catch (Exception ex) {
            log.error("Failed to delete client with id: {}. Error details: {}::ClientRepositoryAdapter", clientId, ex.getMessage());
            return false;
        }
    }

    @Override
    public Client findClientByEmail(String email) throws ClientNotFoundException {
        log.info("Finding client by email: {}::ClientRepositoryAdapter", email);
        return repository.findByEmail(email).map(mapper::toDomainModel)
                .orElseThrow(() -> new ClientNotFoundException(ErrorCodeEnum.ON0005.getCode(),
                        ErrorCodeEnum.ON0005.getMessage()));
    }

    @Override
    public Client getClientById(UUID id) throws ClientNotFoundException {
        log.info("Finding client by id: {}::ClientRepositoryAdapter", id);
        return repository.findById(id).map(mapper::toDomainModel)
                .orElseThrow(() -> new ClientNotFoundException(ErrorCodeEnum.ON0005.getCode(),
                        ErrorCodeEnum.ON0005.getMessage()));
    }

    @Override
    public List<Client> listClients() {
        List<ClientEntity> clientEntities = repository.findAll();
        return mapper.toDomainModelList(clientEntities);
    }

    @Override
    public Client updateClient(Client client) throws ClientNotFoundException {
        try {
            log.info("Updating client with id: {}::ClientRepositoryAdapter", client.getId());
            var existingClientEntity = repository.findById(client.getId())
                    .orElseThrow(() -> new ClientNotFoundException(ErrorCodeEnum.ON0005.getCode(),
                            ErrorCodeEnum.ON0005.getMessage()));

            var updatedClientEntity = mapper.toEntityUpdate(client);
            updatedClientEntity.setPassword(passwordEncoder.encode(client.getPassword()));
            updatedClientEntity.setCreatedAt(existingClientEntity.getCreatedAt());

            var savedClientEntity = repository.save(updatedClientEntity);
            log.info("Client with id: {} updated successfully::ClientRepositoryAdapter", client.getId());
            return mapper.toDomainModel(savedClientEntity);
        } catch (ClientNotFoundException ex) {
            log.error("Client not found with id: {}. Error details: {}::ClientRepositoryAdapter", client.getId(), ex.getMessage());
            throw ex;
        } catch (RuntimeException ex) {
            log.error("Error updating client with id: {}. Error details: {}::ClientRepositoryAdapter", client.getId(), ex.getMessage());
            return null;
        }
    }
}
