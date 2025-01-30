package com.api.customersupport.infrastructure.security;

import com.api.customersupport.application.ports.input.agent.FindAgentByEmailUseCase;
import com.api.customersupport.application.ports.input.clients.FindClientByEmailUseCase;
import com.api.customersupport.domain.models.Client;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    // Dependency Injection
    private final TokenService tokenService;
    private final FindClientByEmailUseCase findClientByEmailUseCase;
    private final FindAgentByEmailUseCase findAgentByEmailUseCase;

    public SecurityFilter(TokenService tokenService, FindClientByEmailUseCase findClientByEmailUseCase
            , FindAgentByEmailUseCase findAgentByEmailUseCase) {
        this.tokenService = tokenService;
        this.findClientByEmailUseCase = findClientByEmailUseCase;
        this.findAgentByEmailUseCase = findAgentByEmailUseCase;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Starting SecurityFilter for request: {}", request.getRequestURI());

        String token = recoverToken(request);
        if (token == null) {
            log.warn("No token found in the request.");
        } else {
            log.info("Token found. Validating...");
            String email = tokenService.validateToken(token);

            if (email != null) {
                log.info("Token validated successfully for email: {}", email);
                try {
                    Object user = findUser(email);

                    if (user != null) {
                        String role = user instanceof Client ? "ROLE_CLIENT" : "ROLE_AGENT";
                        log.info("User found: {}, Role: {}", user.getClass().getSimpleName(), role);

                        var authorities = Collections.singletonList(new SimpleGrantedAuthority(role));
                        var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        log.info("Authentication set successfully for user: {}", email);
                    } else {
                        log.warn("No user found for email: {}", email);
                    }
                } catch (Exception ex) {
                    log.error("Error during authentication process for email: {}", email, ex);
                    SecurityContextHolder.clearContext();
                }
            } else {
                log.warn("Token validation failed.");
            }
        }

        filterChain.doFilter(request, response);
        log.info("Finished processing SecurityFilter for request: {}", request.getRequestURI());
    }

    private Object findUser(String email) {
        log.info("Attempting to find user with email: {}", email);

        try {
            Object client = findClientByEmailUseCase.findClientByEmail(email);
            log.info("Client found: {}", email);
            return client;
        } catch (Exception e) {
            log.warn("Client not found for email: {}, trying as Agent...", email);

            try {
                Object agent = findAgentByEmailUseCase.findAgentByEmail(email);
                log.info("Agent found: {}", email);
                return agent;
            } catch (Exception ex) {
                log.error("No user found for email: {}", email, ex);
                return null;
            }
        }
    }

    private String recoverToken(HttpServletRequest request) {
        log.info("Recovering token from request headers...");
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            log.warn("Authorization header is missing.");
            return null;
        }

        String token = authHeader.replace("Bearer ", "");
        log.info("Token recovered successfully.");
        return token;
    }
}
