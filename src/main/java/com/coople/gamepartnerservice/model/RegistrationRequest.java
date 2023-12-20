package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;


/**
 * A data transfer object (DTO) representing a registration request for a new user.
 * This class is immutable and is typically used to transfer user registration data between layers of an application.
 * It is annotated with Lombok's @Getter and @Builder to automatically generate getters and a builder.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Getter
@Builder
public class RegistrationRequest {

    /**
     * The name of the user.
     */
    private final String name;

    /**
     * The email address of the user.
     */
    private final String email;

    /**
     * The password chosen by the user for authentication.
     */
    private final String password;

    /**
     * The role assigned to the user during registration.
     */
    private final String role;
}
