package com.api.customersupport.infrastructure.security;

import com.api.customersupport.domain.enums.ErrorCodeEnum;
import com.api.customersupport.domain.exceptions.JwtCreateException;
import com.api.customersupport.domain.models.Agent;
import com.api.customersupport.domain.models.Client;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.api.customersupport.infrastructure.utils.Utils.log;

@Service
public class TokenService {
    @Value("${secret-key}")
    private String secret;

    public String generateToken(Object user) throws JwtCreateException {
        try {
            String email = null;

            if (user instanceof Client) {
                email = ((Client) user).getEmail();
            } else if (user instanceof Agent) {
                email = ((Agent) user).getEmail();
            } else {
                throw new JwtCreateException("Invalid user type", "User must be either a Client or Agent");
            }

            Algorithm algorithm = Algorithm.HMAC256(secret);
            log.info("Generating token for user: {}", email);
            return JWT.create()
                    .withIssuer("login-auth-api")
                    .withSubject(email)
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JwtCreateException(ErrorCodeEnum.JW0001.getCode(),
                    ErrorCodeEnum.concatError(exception.getMessage(), ErrorCodeEnum.JW0001));
        }
    }


    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("login-auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            log.error("Invalid token: {}", exception.getMessage());
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
