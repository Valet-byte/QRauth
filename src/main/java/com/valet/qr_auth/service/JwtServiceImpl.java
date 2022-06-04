package com.valet.qr_auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.valet.qr_auth.model.Point;
import com.valet.qr_auth.repo.PointRepo;
import com.valet.qr_auth.service.interfaces.JwtService;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService{

    @Value("${secret}")
    private String secret;
    @Value("${validTime}")
    private Long validTime;

    private final PointRepo pointRepo;

    @Override
    public String generateToken(Long userId, Long pointId) {

        Point point = pointRepo.findById(pointId);

        if (point.getCreatorId().equals(userId) && point.getActualTime().isAfter(LocalDateTime.now().plusHours(3))){
            return JWT.create()
                    .withPayload(Map.of(
                            "create_time", System.currentTimeMillis(),
                            "creatorId", userId,
                            "pointId", pointId
                    ))
                    .withExpiresAt(new Date(System.currentTimeMillis() + validTime))
                    .sign(Algorithm.HMAC512(secret.getBytes()));
        } else return "Error";
    }

    @Override
    public boolean validToken(String token) {
        try {
           String token2 = JWT.require(Algorithm.HMAC512(secret.getBytes())).build()
                    .verify(token).getToken();

            return token.equals(token2);
        } catch (Exception ignore){
            return false;
        }
    }
}
