package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;

/**
 * A data transfer object (DTO) representing a request for user login.
 * This class is immutable and is typically used to transfer login data between layers of an application.
 * It is annotated with Lombok's @Getter and @Builder to automatically generate getters and a builder.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Getter
@Builder
public class LoginRequest {

    /**
     * The email address associated with the user's account.
     */
    private final String email;

    /**
     * The password provided by the user for authentication.
     */
    private final String password;
}
