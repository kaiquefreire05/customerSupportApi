package com.api.customersupport.application.services.client;

import com.api.customersupport.application.ports.input.clients.DeleteClientUseCase;
import com.api.customersupport.application.ports.output.ClientRepositoryPort;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;

import java.util.UUID;

import static com.api.customersupport.infrastructure.utils.Utils.log;

public class DeleteClientService implements DeleteClientUseCase {
    // Dependency Injection
    private final ClientRepositoryPort repository;

    public DeleteClientService(ClientRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public void deleteClient(UUID clientId) throws InternalServerErrorException {
        log.info("Starting of client deletion::DeleteClientImpl");
        try {
            boolean success = repository.deleteClient(clientId);
            if (!success) {
                throw new InternalServerErrorException(ErrorCodeEnum.ON0004.getCode(), ErrorCodeEnum.ON0004.getMessage());
            }

        } catch (Exception ex) {
            log.info("Error deleting client with ID: {}. Error details: {}", clientId, ex.getMessage());
            throw new InternalServerErrorException(ErrorCodeEnum.ON0004.getCode()
                    , ErrorCodeEnum.concatError(ex.getMessage(), ErrorCodeEnum.ON0004));
        }
    }
}
