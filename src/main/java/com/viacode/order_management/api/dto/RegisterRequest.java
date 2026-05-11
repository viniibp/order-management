package com.viacode.order_management.api.dto;

import com.viacode.order_management.domain.auth.model.UserRole;

public record RegisterRequest(
    String email,
    String password,
    String name,
    UserRole role
) {}
