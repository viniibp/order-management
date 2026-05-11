package com.viacode.order_management.infra.security;

import com.viacode.order_management.domain.auth.port.TokenProviderPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtAdapter implements TokenProviderPort {

    @Value("${jwt.secret:defaultSecretKeyForDevelopmentOnlyChangeInProduction}")
    private String secretKey;

    @Value("${jwt.expiration:86400000}")
    private long expiration;

    @Override
    public String generateToken(String email) {
        SecretKey key = Keys.hmacShaKeyFor(
            secretKey.getBytes(StandardCharsets.UTF_8)
        );

        return Jwts.builder()
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(key)
            .compact();
    }
}
