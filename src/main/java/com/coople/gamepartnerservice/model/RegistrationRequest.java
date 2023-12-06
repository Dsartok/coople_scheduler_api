package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegistrationRequest {
    private final String name;
    private final String email;
    private final String password;
    private final String role;
}
