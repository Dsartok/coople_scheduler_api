package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;

/**
 * A data transfer object (DTO) representing a request to reset a user's password.
 * This class is immutable and is typically used to transfer reset password data between layers of an application.
 * It is annotated with Lombok's @Getter and @Builder to automatically generate getters and a builder.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Getter
@Builder
public class ResetPasswordRequest {

    /**
     * The email address of the user requesting a password reset.
     */
    private final String email;

    /**
     * The user's old password, if applicable (for security checks).
     */
    private final String oldPassword;

    /**
     * The new password the user wants to set.
     */
    private final String newPassword;
}
