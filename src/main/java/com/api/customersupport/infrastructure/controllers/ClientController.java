package com.api.customersupport.infrastructure.controllers;

import com.api.customersupport.domain.models.Client;
import com.api.customersupport.domain.models.SupportTicket;
import com.api.customersupport.infrastructure.dto.requests.CreateClientRequest;
import com.api.customersupport.infrastructure.dto.requests.UpdateClientRequest;
import com.api.customersupport.infrastructure.dto.response.BaseResponse;
import com.api.customersupport.infrastructure.mapper.ClientMapper;
import com.api.customersupport.usecases.client.ClientEmailAvailableUseCase;
import com.api.customersupport.usecases.client.CreateClientUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {
    // Dependency Injection
    private final CreateClientUseCase createClientUseCase;
    private final ClientEmailAvailableUseCase clientEmailAvailableUseCase;
    private final ClientMapper clientMapper;

    public ClientController(CreateClientUseCase createClientUseCase
            , ClientEmailAvailableUseCase clientEmailAvailableUseCase, ClientMapper clientMapper) {
        this.createClientUseCase = createClientUseCase;
        this.clientEmailAvailableUseCase = clientEmailAvailableUseCase;
        this.clientMapper = clientMapper;
    }

    // Controller methods
    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/create")
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

    @PreAuthorize("hasRole('Agent')")
    @PostMapping("/update")
    public ResponseEntity<BaseResponse<String>> updateCliente(@Valid @RequestBody UpdateClientRequest request) {
        log.info("Request received to update client::ClientController");
        try {

        } catch (Exception ex) {

        }
    }
}
