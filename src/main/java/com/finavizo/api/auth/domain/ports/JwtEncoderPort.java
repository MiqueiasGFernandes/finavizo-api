package com.finavizo.api.auth.domain.ports;

import java.util.UUID;

public interface JwtEncoderPort {
    String sign(UUID userId);
}
