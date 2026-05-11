package com.viacode.order_management.domain.auth.service;

import com.viacode.order_management.domain.auth.model.User;
import com.viacode.order_management.domain.auth.model.UserRole;
import com.viacode.order_management.domain.auth.port.TokenProviderPort;
import com.viacode.order_management.domain.auth.port.UserRepositoryPort;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepositoryPort userRepositoryPort;
    private final TokenProviderPort tokenProviderPort;

    public AuthService(
        UserRepositoryPort userRepositoryPort,
        TokenProviderPort tokenProviderPort
    ) {
        this.userRepositoryPort = userRepositoryPort;
        this.tokenProviderPort = tokenProviderPort;
    }

    public String register(
        String email,
        String password,
        String name,
        UserRole role
    ) {
        Optional<User> userExists = this.userRepositoryPort.findByEmail(email);

        if (userExists.isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        User newUser = new User(email, password, name, role);

        User savedUser = this.userRepositoryPort.save(newUser);

        return tokenProviderPort.generateToken(savedUser.getEmail());
    }

    public String login(String email, String password) {
        Optional<User> userOpt = this.userRepositoryPort.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid password.");
        }

        return tokenProviderPort.generateToken(email);
    }
}
