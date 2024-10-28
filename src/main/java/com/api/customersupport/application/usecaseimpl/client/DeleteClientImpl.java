package com.api.customersupport.application.usecaseimpl.client;

import com.api.customersupport.application.gateway.client.DeleteClientGateway;
import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.exceptions.InternalServerErrorException;
import com.api.customersupport.usecases.client.DeleteClientUseCase;
import com.api.customersupport.usecases.client.FindClientByIdUseCase;

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
        try {
            /*
            // Verify if the client exists
            var client = findClientByIdUseCase.findClientById(clientId);


            if (client == null) {
                throw new ClientNotFoundException(ErrorCodeEnum.ON0005.getCode(), ErrorCodeEnum.ON0005.getMessage());
            }*/
            // TODO: Provavelmente o código acima vai ser removido, pois vai existir uma verificação interna no método findClientById

            boolean success = deleteClientGateway.deleteClient(clientId);
            if (!success) {
                throw new InternalServerErrorException(ErrorCodeEnum.ON0004.getCode(), ErrorCodeEnum.ON0004.getMessage());
            }

        } catch (Exception ex) {
            //logger.info("Error deleting client with ID: " + clientId, e);
            throw new InternalServerErrorException(ErrorCodeEnum.ON0004.getCode()
                    , ErrorCodeEnum.concatError(ex.getMessage(), ErrorCodeEnum.ON0004));
        }
    }
}
