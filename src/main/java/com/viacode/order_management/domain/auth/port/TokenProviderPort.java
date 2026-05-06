package com.viacode.order_management.domain.auth.port;

public interface TokenProviderPort {
    String generateToken(String email);
}
