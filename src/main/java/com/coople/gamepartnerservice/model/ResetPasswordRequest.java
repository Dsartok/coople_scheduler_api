package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResetPasswordRequest {
    private final String email;
    private final String oldPassword;
    private final String newPassword;
}
