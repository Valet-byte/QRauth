package com.valet.qrmainserver.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.qrmainserver.exception.TokenNotValidException;
import com.valet.qrmainserver.model.Payload;
import com.valet.qrmainserver.service.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Value("${secret}")
    private String secret;
    @Value("${validTime}")
    private Long validTime;
    private final ObjectMapper mapper;

    @Override
    public String createToken(double x, double y, int radius, String organizationId, String teamId) {
        Map<String, Object> payload = new HashMap<>(5);
        payload.put("x", x);
        payload.put("y", y);
        payload.put("radius", radius);
        payload.put("organizationId", organizationId);
        payload.put("teamId", teamId);



        return JWT.create()
                .withPayload(payload)
                .withExpiresAt(new Date(System.currentTimeMillis() + validTime))
                .sign(Algorithm.HMAC512(secret));
    }

    @Override
    public Payload getPayload(String token) throws TokenNotValidException {
        try {
            Payload payload;

            payload = mapper.readValue(new String(Base64.getDecoder().decode(JWT.require(Algorithm.HMAC512(secret.getBytes(StandardCharsets.UTF_8)))
                            .acceptExpiresAt(validTime)
                            .build()

                            .verify(token)
                            .getPayload()), StandardCharsets.UTF_8), Payload.class);
            return payload;
        } catch (Exception e){
            e.printStackTrace();
            throw new TokenNotValidException();
        }
    }
}
