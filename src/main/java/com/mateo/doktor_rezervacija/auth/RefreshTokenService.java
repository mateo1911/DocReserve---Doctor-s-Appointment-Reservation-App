package com.mateo.doktor_rezervacija.auth;

import com.mateo.doktor_rezervacija.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.HexFormat;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${app.jwt.refresh-expiration}")
    private long refreshExpirationMs;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String createRefreshToken(User user) {
        String raw = UUID.randomUUID().toString().replace("-", "");
        String hash = sha256(raw);

        RefreshToken token = new RefreshToken(hash, user, Instant.now().plusMillis(refreshExpirationMs));
        refreshTokenRepository.save(token);

        return raw;
    }

    public User validateAndGetUser(String rawRefreshToken) {
        String hash = sha256(rawRefreshToken);

        RefreshToken token = refreshTokenRepository.findByTokenHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token ne postoji"));

        if (token.isRevoked()) throw new IllegalArgumentException("Refresh token opozvan");
        if (token.getExpiresAt().isBefore(Instant.now())) throw new IllegalArgumentException("Refresh token istekao");

        return token.getUser();
    }

    public void revoke(String rawRefreshToken) {
        String hash = sha256(rawRefreshToken);

        RefreshToken token = refreshTokenRepository.findByTokenHash(hash)
                .orElseThrow(() -> new IllegalArgumentException("Refresh token ne postoji"));

        token.setRevoked(true);
        refreshTokenRepository.save(token);
    }

    private String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
