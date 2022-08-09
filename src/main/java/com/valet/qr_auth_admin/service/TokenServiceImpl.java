package com.valet.qr_auth_admin.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.valet.qr_auth_admin.service.interfaces.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${secret}")
    private String secret;
    @Value("${validTime}")
    private Long validTime;

    @Override
    public Mono<String> createToken(double x, double y, int radius, long adminId, String organization) {
        Map<String, Object> payload = new HashMap<>(5);
        payload.put("x", x);
        payload.put("y", y);
        payload.put("radius", radius);
        payload.put("adminId", adminId);
        payload.put("organization", organization);


        return Mono.just(JWT.create()
                .withPayload(payload)
                .withExpiresAt(new Date(System.currentTimeMillis() + validTime))
                .sign(Algorithm.HMAC512(secret.getBytes(StandardCharsets.UTF_8))));
    }
}
