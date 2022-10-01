package com.valet.qrmainserver.service.interfaces;

import com.valet.qrmainserver.exception.TokenNotValidException;
import com.valet.qrmainserver.model.Payload;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TokenService {
    String createToken(double x, double y, int radius, String organizationId, String teamId);
    Payload getPayload(String token) throws TokenNotValidException;
}
