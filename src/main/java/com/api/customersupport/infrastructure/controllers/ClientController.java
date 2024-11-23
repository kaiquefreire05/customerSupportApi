package com.api.customersupport.infrastructure.controllers;

import com.api.customersupport.domain.exceptions.ClientNotFoundException;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.infrastructure.dto.requests.client.CreateClientRequest;
import com.api.customersupport.infrastructure.dto.requests.client.UpdateClientRequest;
import com.api.customersupport.infrastructure.dto.response.BaseResponse;
import com.api.customersupport.infrastructure.mapper.ClientMapper;
import com.api.customersupport.usecases.client.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {
    // Dependency Injection
    private final CreateClientUseCase createClientUseCase;
    private final ClientEmailAvailableUseCase clientEmailAvailableUseCase;
    private final ClientMapper clientMapper;
    private final FindClientByIdUseCase findClientByIdUseCase;
    private final ListClientsUseCase listClientsUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;
    private final FindClientByEmailUseCase findClientByEmailUseCase;

    public ClientController(CreateClientUseCase createClientUseCase, ClientEmailAvailableUseCase clientEmailAvailableUseCase
            , ClientMapper clientMapper, FindClientByIdUseCase findClientByIdUseCase, ListClientsUseCase listClientsUseCase
            , UpdateClientUseCase updateClientUseCase, DeleteClientUseCase deleteClientUseCase, FindClientByEmailUseCase findClientByEmailUseCase) {
        this.createClientUseCase = createClientUseCase;
        this.clientEmailAvailableUseCase = clientEmailAvailableUseCase;
        this.clientMapper = clientMapper;
        this.findClientByIdUseCase = findClientByIdUseCase;
        this.listClientsUseCase = listClientsUseCase;
        this.updateClientUseCase = updateClientUseCase;
        this.deleteClientUseCase = deleteClientUseCase;
        this.findClientByEmailUseCase = findClientByEmailUseCase;
    }


    // Controller methods
    @GetMapping("/all")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<List<Client>>> getAll() {
        log.info("Request received to get all entities client::ClientController");
        try {
            List<Client> clients = listClientsUseCase.listClients();
            return ResponseEntity.ok().body(BaseResponse.<List<Client>>builder().success(true)
                    .message("All clients successfully returned.").result(clients).build());
        } catch (Exception ex) {
            log.error("Error occurred while trying to get all clients. Error details: {}::ClientController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<List<Client>>builder().success(false)
                    .message("Error while trying to get all clients").build());
        }
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<BaseResponse<Client>> getById(@PathVariable UUID uuid) {
        log.info("Request received to get a entity by id client::ClientController");
        try {
            log.info("Starting the getting of client by id::ClientController");
            Client client = findClientByIdUseCase.findClientById(uuid);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<Client>builder().success(true)
                    .result(client).message("Client with id " + uuid + " successfully returned.").build());

        } catch (ClientNotFoundException ex) {
            log.warn("Client with id {} not found::ClientController", uuid);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.<Client>builder().success(false)
                    .result(null).message("Client with id " + uuid + " not found.").build());

        } catch (Exception ex) {
            log.error("Error occurred while trying to get a client by its id. Error details: {}::ClientController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<Client>builder().success(false)
                    .result(null).message("Error while trying to get a client by id").build());
        }
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BaseResponse<Client>> getByEmail(@PathVariable String email) {
        log.info("Request received to get a entity by email client::ClientController");
        try {
            log.info("Starting the getting of client by email::ClientController");
            Client client = findClientByEmailUseCase.findClientByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<Client>builder().success(true)
                    .result(client).message("Client with email " + email + " successfully returned.").build());

        } catch (ClientNotFoundException ex) {
            log.warn("Client with email {} not found::ClientController", email);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponse.<Client>builder().success(false)
                    .message("Client with email " + email + " not found").build());

        } catch (Exception ex) {
            log.error("Error occurred while trying to get a client by its email. Error details: {}::ClientController"
                    , ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<Client>builder().success(false)
                    .message("Error while trying to get a client by email").build());
        }
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<BaseResponse<Boolean>> deleteClient(@PathVariable UUID uuid) {
        log.info("Request received to delete an entity client::ClientController");
        try {
            deleteClientUseCase.deleteClient(uuid);
            return ResponseEntity.ok().body(BaseResponse.<Boolean>builder().success(true)
                    .result(true).message("A client with id " + uuid + " successfully removed.").build());
        } catch (Exception ex) {
            log.error("Error occurred while trying to delete a client. Error details: {}::ClientController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<Boolean>builder().success(false)
                    .result(false).message("Error while trying to delete a client").build());
        }
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BaseResponse<String>> createClient(@Valid @RequestBody CreateClientRequest request) {
        log.info("Request received to create client::ClientController");
        try {
            log.info("Checking if email is available::ClientController");
            clientEmailAvailableUseCase.isEmailAvailable(request.email()); // Check if email is available
            log.info("Email is available::ClientController");
            createClientUseCase.createClient(clientMapper.toDomainCreateRequest(request)); // Create client
            log.info("Client created successfully::ClientController");
            return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.<String>builder().success(true)
                    .message("Client created successfully.").build());

        } catch (Exception ex) {
            log.error("Error occurred while creating client. Error details: {}::ClientController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<String>builder()
                    .success(false).message("Error occurred while creating client.").build());
        }
    }

    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<BaseResponse<String>> updateClient(@Valid @RequestBody UpdateClientRequest request) {
        log.info("Request received to update client::ClientController");
        try {
            Client foundClient = findClientByIdUseCase.findClientById(request.id());

            log.info("Updating client details::ClientController");
            foundClient.setName(request.name());
            foundClient.setEmail(request.email());
            foundClient.setPassword(request.password());
            foundClient.setPhone(request.phone());
            foundClient.setAddress(request.address());
            foundClient.setUpdatedAt(LocalDateTime.now());

            updateClientUseCase.updateClient(foundClient);
            log.info("Client updated successfully::ClientController");
            return ResponseEntity.status(HttpStatus.OK).body(BaseResponse.<String>builder().success(true)
                    .message("Client updated successfully.").build());

        } catch (Exception ex) {
            log.error("Error occurred while updating client. Error details: {}::ClientController", ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponse.<String>builder()
                    .success(false).message("Error occurred while updating client.").build());
        }
    }
}
