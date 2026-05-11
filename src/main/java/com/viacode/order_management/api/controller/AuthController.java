package com.viacode.order_management.api.controller;

import com.viacode.order_management.api.dto.AuthResponse;
import com.viacode.order_management.api.dto.LoginRequest;
import com.viacode.order_management.api.dto.RegisterRequest;
import com.viacode.order_management.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
        @RequestBody RegisterRequest request
    ) {
        String token = authService.register(
            request.email(),
            request.password(),
            request.name(),
            request.role()
        );

        return ResponseEntity.ok(
            new AuthResponse(token, request.email(), request.name())
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
        @RequestBody LoginRequest request
    ) {
        String token = authService.login(request.email(), request.password());

        return ResponseEntity.ok(
            new AuthResponse(token, request.email(), "User")
        );
    }
}
