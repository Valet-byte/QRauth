package com.valet.qr_auth_main_server.service.interfaces;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TokenService {
    Mono<String> createToken(double x, double y, int radius, long adminId, Long organization, LocalDateTime now);
}
