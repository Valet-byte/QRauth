package com.valet.qr_auth_main_server.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.qr_auth_main_server.model.Payload;
import com.valet.qr_auth_main_server.model.Record;
import com.valet.qr_auth_main_server.model.User;
import com.valet.qr_auth_main_server.model.changeAction.ActionType;
import com.valet.qr_auth_main_server.repo.RecordRepo;
import com.valet.qr_auth_main_server.service.interfaces.ActionService;
import com.valet.qr_auth_main_server.service.interfaces.GeographyService;
import com.valet.qr_auth_main_server.service.interfaces.RecordService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class RecordServiceImpl implements RecordService {

    private final String secret;
    private final GeographyService geographyService;
    private final ActionService actionService;
    private final RecordRepo recordRepo;

    public RecordServiceImpl(@Value("${secret}")String secret,
                             GeographyService geographyService,
                             ActionService actionService, RecordRepo recordRepo) {
        this.secret = secret;
        this.geographyService = geographyService;
        this.actionService = actionService;
        this.recordRepo = recordRepo;
    }

    @Override
    public Mono<Boolean> addRecord(double x, double y, String token, User user, LocalDateTime now) {

        try {
            Payload payload;

            ObjectMapper mapper = new ObjectMapper();

            payload = mapper.readValue(new String(Base64.getDecoder().decode(JWT.require(Algorithm.HMAC512(secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .verify(token)
                    .getPayload()), StandardCharsets.UTF_8), Payload.class);

            if (payload.getOrganization().equals(user.getOrganization().getId()) &&
                    geographyService.getDistance(payload.getX(), payload.getY(), x, y) <= payload.getRadius()){

                return actionService.doAction(
                        actionService.createActionNotRegistrationInRedis(null, Record.builder()
                                .adminId(payload.getAdminId())
                                .dateTime(now)
                                .userId(user.getId())
                                .build(), ActionType.ADD_RECORD)
                );
            }else{
                throw new RuntimeException("Не совпадают организации " + user + " " + payload);
            }

        } catch (Exception e){
            e.printStackTrace();
            return Mono.error(new Throwable("Что-то пошло не так"));
        }
    }

    @Override
    public Flux<Record> getAllRecord(Long adminId, LocalDate from, LocalDate to) {
        return recordRepo.findAllRecordWhoMadeFromAndTo(adminId, from, to);
    }
}
