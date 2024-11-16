package com.api.customersupport.application.usecaseimpl.client;

import com.api.customersupport.application.gateway.client.DeleteClientGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.usecases.client.DeleteClientUseCase;
import com.api.customersupport.usecases.client.FindClientByIdUseCase;
import static com.api.customersupport.infrastructure.utils.Utils.log;

import java.util.UUID;

public class DeleteClientImpl implements DeleteClientUseCase {
    // Dependency Injection
    private final DeleteClientGateway deleteClientGateway;
    private final FindClientByIdUseCase findClientByIdUseCase;

    public DeleteClientImpl(DeleteClientGateway deleteClientGateway
            , FindClientByIdUseCase findClientByIdUseCase) {
        this.deleteClientGateway = deleteClientGateway;
        this.findClientByIdUseCase = findClientByIdUseCase;
    }

    @Override
    public void deleteClient(UUID clientId) throws InternalServerErrorException {
        log.info("Starting of client deletion::DeleteClientImpl");
        try {
            boolean success = deleteClientGateway.deleteClient(clientId);
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
