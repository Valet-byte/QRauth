package com.valet.qr_auth_user.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.qr_auth_user.model.Payload;
import com.valet.qr_auth_user.model.Record;
import com.valet.qr_auth_user.model.User;
import com.valet.qr_auth_user.repo.RecordRepo;
import com.valet.qr_auth_user.service.interfaces.GeographyService;
import com.valet.qr_auth_user.service.interfaces.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class RecordServiceImpl implements RecordService {

    @Value("${secret}")
    private String secret;

    @Autowired
    private RecordRepo recordRepo;
    @Autowired
    private GeographyService geographyService;

    @Override
    public Mono<Record> addRecord(double x, double y, String token, User user) {

        try {
            Payload payload;

            ObjectMapper mapper = new ObjectMapper();

            payload = mapper.readValue(new String(Base64.getDecoder().decode(JWT.require(Algorithm.HMAC512(secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .verify(token)
                    .getPayload()), StandardCharsets.UTF_8), Payload.class);

            if (payload.getOrganization().equals(user.getOrganization()) &&
                    geographyService.getDistance(payload.getX(), payload.getY(), x, y) <= payload.getRadius()){

                return recordRepo.save(Record.builder()
                        .adminId(payload.getAdminId())
                        .dateTime(LocalDateTime.now())
                        .userId(user.getId())
                        .build());
            }else{
                throw new RuntimeException("Не совпадают организации " + user + " " + payload);
            }

        } catch (Exception e){
            e.printStackTrace();
            return Mono.error(new Throwable("Что-то пошло не так"));
        }



    }
}
