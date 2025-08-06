package com.challenge.forohub.controller;

import com.challenge.forohub.domain.user.UserAuthenticationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<String> testEndpoint(@RequestBody UserAuthenticationDTO dto) {
        return ResponseEntity.ok("Recibido login: " + dto.login());
    }
}