package com.viacode.order_management.domain.auth.port;

import com.viacode.order_management.domain.auth.model.User;
import java.util.Optional;

public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);

    User save(User user);
}
