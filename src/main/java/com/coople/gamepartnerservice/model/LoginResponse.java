package com.coople.gamepartnerservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class LoginResponse {
    private String token;
}
