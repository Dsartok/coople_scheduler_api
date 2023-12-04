package com.coople.gamepartnerservice.controller;

import com.coople.gamepartnerservice.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @GetMapping("/")
    public String hello() {
        return "Public endpoint";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Public endpoint";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal UserPrincipal principal) {
        return "User endpoint. Your Email is: "
                + principal.getEmail() + " your ID: " + principal.getUserId();
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal) {
        return "Admin endpoint. Your ID: " + principal.getUserId();
    }
}
