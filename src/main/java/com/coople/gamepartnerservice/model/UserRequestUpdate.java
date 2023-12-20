package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * A data transfer object (DTO) representing a request to update user entity.
 * This class is annotated with Lombok's @Getter, @Setter and @Builder
 * to automatically generate getters, setters and a builder.
 *
 * <p>The field in this class include information name, email, password, extraInfo.
 *
 * @version 1.0
 * @since 14-12-2023
 * @author maupa13
 */
@Getter
@Setter
@Builder
public class UserRequestUpdate {

    private String name;

    private String email;

    private String password;

    private String extraInfo;
}
