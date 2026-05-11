package com.viacode.order_management.infra.persistence;

import com.viacode.order_management.domain.auth.model.User;
import com.viacode.order_management.domain.auth.port.UserRepositoryPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;

    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(this::toDomain);
    }

    public User save(User user) {
        UserEntity entity = toEntity(user);
        entity = userJpaRepository.save(entity);
        return toDomain(entity);
    }

    private User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setName(entity.getName());
        user.setRole(entity.getRole());
        user.setCreatedAt(entity.getCreatedAt());
        return user;
    }

    private UserEntity toEntity(User user) {
        return UserEntity.builder()
            .id(user.getId())
            .email(user.getEmail())
            .password(user.getPassword())
            .name(user.getName())
            .role(user.getRole())
            .createdAt(user.getCreatedAt())
            .build();
    }
}
