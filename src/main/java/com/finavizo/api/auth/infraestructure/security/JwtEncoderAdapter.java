package com.finavizo.api.auth.infraestructure.security;

import java.time.Instant;
import java.util.UUID;

import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import com.finavizo.api.auth.domain.ports.JwtEncoderPort;

@Component
public class JwtEncoderAdapter implements JwtEncoderPort {
    private final JwtEncoder jwtEncoder;

    public JwtEncoderAdapter(
            JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public String sign(UUID userId) {
        var now = Instant.now();

        var claims = JwtClaimsSet.builder()
                .issuer("finavizo")
                .subject(userId.toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3000))
                .build();

        var header = JwsHeader
                .with(MacAlgorithm.HS256)
                .build();

        return jwtEncoder.encode(
                JwtEncoderParameters.from(header, claims)).getTokenValue();
    }

}
