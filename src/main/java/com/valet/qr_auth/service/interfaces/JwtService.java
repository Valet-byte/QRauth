package com.valet.qr_auth.service.interfaces;

public interface JwtService {
    String generateToken(Long userId, Long pointId);
    boolean validToken(String token);
}
