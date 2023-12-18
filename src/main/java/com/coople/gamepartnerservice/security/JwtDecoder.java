package com.coople.gamepartnerservice.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Component responsible for decoding and verifying JWT tokens.
 * This class is annotated with Spring's @Component, making it a Spring Bean.
 * It is constructed using Lombok's @RequiredArgsConstructor, injecting the required JwtProperties.
 * The class uses the provided secret key from JwtProperties to decode and verify JWT tokens.
 *
 * <p>Example usage:
 * <pre>{@code
 * // Injected automatically by Spring
 * JwtDecoder jwtDecoder = new JwtDecoder(jwtProperties);
 *
 * // Decode and verify a JWT token
 * DecodedJWT decodedJWT = jwtDecoder.decode("dfdAZA234EgdDs1faW3R2Q3FQ...");
 * }</pre>
 *
 * @see org.springframework.stereotype.Component
 * @see JwtProperties
 * @see com.auth0.jwt.JWT
 * @see com.auth0.jwt.algorithms.Algorithm
 * @see com.auth0.jwt.interfaces.DecodedJWT
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Component
@RequiredArgsConstructor
public class JwtDecoder {

    /**
     * The configuration properties for JWT, including the secret key.
     */
    private final JwtProperties properties;

    /**
     * Decodes and verifies the provided JWT token using the configured secret key.
     *
     * @param token The JWT token to decode and verify.
     * @return The decoded JWT representation.
     * @see com.auth0.jwt.JWT
     * @see com.auth0.jwt.algorithms.Algorithm
     * @see com.auth0.jwt.interfaces.DecodedJWT
     */
    public DecodedJWT decode(String token) {
        return JWT.require(Algorithm.HMAC256(properties.getSecretKey()))
                .build()
                .verify(token);
    }
}
