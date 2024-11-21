package com.api.customersupport.infrastructure.controllers;

import com.api.customersupport.domain.exceptions.*;
import com.api.customersupport.infrastructure.dto.requests.auth.LoginRequest;
import com.api.customersupport.infrastructure.dto.requests.client.CreateClientRequest;
import com.api.customersupport.infrastructure.mapper.ClientMapper;
import com.api.customersupport.infrastructure.security.TokenService;
import com.api.customersupport.usecases.agent.FindAgentByEmailUseCase;
import com.api.customersupport.usecases.client.CreateClientUseCase;
import com.api.customersupport.usecases.client.FindClientByEmailUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    // Dependency Injection
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final FindAgentByEmailUseCase findAgentByEmailUseCase;
    private final FindClientByEmailUseCase findClientByEmailUseCase;
    private final CreateClientUseCase createClientUseCase;
    private final ClientMapper clientMapper;

    public AuthController(PasswordEncoder passwordEncoder, TokenService tokenService
            , FindAgentByEmailUseCase findAgentByEmailUseCase, FindClientByEmailUseCase findClientByEmailUseCase
            , CreateClientUseCase createClientUseCase, ClientMapper clientMapper) {
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.findAgentByEmailUseCase = findAgentByEmailUseCase;
        this.findClientByEmailUseCase = findClientByEmailUseCase;
        this.createClientUseCase = createClientUseCase;
        this.clientMapper = clientMapper;
    }


    // Controller Methods
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {

            var agent = findAgentByEmailUseCase.findAgentByEmail(request.email());
            if (passwordEncoder.matches(request.password(), agent.getPassword())) {
                String token = tokenService.generateToken(agent);
                return ResponseEntity.ok(token);
            }
        } catch (RuntimeException e) {

            try {
                var client = findClientByEmailUseCase.findClientByEmail(request.email());
                if (passwordEncoder.matches(request.password(), client.getPassword())) {
                    String token = tokenService.generateToken(client);
                    return ResponseEntity.ok(token);
                }
            } catch (RuntimeException | JwtCreateException | ClientNotFoundException ex) {
                return ResponseEntity.badRequest().body("Invalid email or password");
            }

        } catch (JwtCreateException | AgentNotFoundException e) {
            throw new RuntimeException(e);

        }

        return ResponseEntity.badRequest().body("Invalid email or password");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateClientRequest request)
            throws EmailInvalidException, PhoneInvalidException, InternalServerErrorException, JwtCreateException {
        try {

            var existingClient = findClientByEmailUseCase.findClientByEmail(request.email());
            return ResponseEntity.badRequest().body("Client with this email already exists");

        } catch (RuntimeException | ClientNotFoundException e) {

            var client = createClientUseCase.createClient(clientMapper.toDomainCreateRequest(request));
            String token = tokenService.generateToken(client);
            return ResponseEntity.ok(token);
        }
    }

}
