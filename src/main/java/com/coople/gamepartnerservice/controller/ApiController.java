package com.coople.gamepartnerservice.controller;

import com.coople.gamepartnerservice.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coople_scheduler_api")
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/")
    public String hello() {
        logger.info("hello accessed.");
        return "Public endpoint";
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        logger.info("publicEndpoint accessed.");
        return "Public endpoint";
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal UserPrincipal principal) {
        logger.info("user endpoint accessed.");
        return "User endpoint. Your Email is: "
                + principal.getEmail() + " your ID: " + principal.getUserId();
    }

    @GetMapping("/admin")
    public String admin(@AuthenticationPrincipal UserPrincipal principal) {
        logger.info("admin endpoint accessed.");
        return "Admin endpoint. Your ID: " + principal.getUserId();
    }
}
