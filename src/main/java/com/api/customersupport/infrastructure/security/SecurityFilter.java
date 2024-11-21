package com.api.customersupport.infrastructure.security;

import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.domain.models.Client;
import com.api.customersupport.usecases.agent.FindAgentByEmailUseCase;
import com.api.customersupport.usecases.client.FindClientByEmailUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

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
        var token = this.recoverToken(request);
        var login = tokenService.validateToken(token);

        if (login != null) {
            try {
                Client client = findClientByEmailUseCase.findClientByEmail(login);
                var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_CLIENT"));
                var authentication = new UsernamePasswordAuthenticationToken(client, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e1) {
                try {
                    Agent agent = findAgentByEmailUseCase.findAgentByEmail(login);
                    var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_AGENT"));
                    var authentication = new UsernamePasswordAuthenticationToken(agent, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                } catch (Exception e2) {
                    SecurityContextHolder.clearContext();
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
