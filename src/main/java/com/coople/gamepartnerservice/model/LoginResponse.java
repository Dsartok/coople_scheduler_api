package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * A data transfer object (DTO) representing a response to a user login request.
 * This class is immutable and is typically used to transfer login response data between layers of an application.
 * It is annotated with Lombok's @Getter, @Builder, and @ToString to automatically generate getters, a builder,
 * and a toString method.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Getter
@Builder
@ToString
public class LoginResponse {

    /**
     * The authentication token associated with a successful login.
     */
    private String token;
}
