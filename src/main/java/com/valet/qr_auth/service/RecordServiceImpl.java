package com.valet.qr_auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.qr_auth.model.Payload;
import com.valet.qr_auth.model.Person;
import com.valet.qr_auth.model.Point;
import com.valet.qr_auth.repo.PersonRepo;
import com.valet.qr_auth.repo.PointRepo;
import com.valet.qr_auth.repo.RecordRepo;
import com.valet.qr_auth.service.interfaces.GeographyService;
import com.valet.qr_auth.service.interfaces.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final RecordRepo recordRepo;

    @Value("${secret}")
    private String secret;

    private final PersonRepo personRepo;
    private final PointRepo pointRepo;
    private final GeographyService geographyService;


    @Override
    public List<Person> getAllRecordsByPointId(Long pointId) {

        return recordRepo.getAllRecordsByPointId(pointId);
    }

    @Override
    public Long addNewRecord(Long userId, String token, String typeRecord, Double x, Double y) {
        String payloadString;
        try {
            payloadString = JWT.require(Algorithm.HMAC512(secret.getBytes()))
                    .build()
                    .verify(token)
                    .getPayload();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        Payload payload;

        ObjectMapper mapper = new ObjectMapper();

        try {
            payload = mapper.readValue(new String(Base64.getDecoder().decode(payloadString), StandardCharsets.UTF_8), Payload.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

        if (personRepo.findById(userId).getOrganization().equals(personRepo.findById(payload.getCreatorId()).getOrganization())) {

            Point point = pointRepo.findById(payload.getPointId());
            System.out.println(point);

            if (geographyService.getDistance(point.getX(), point.getY(), x, y) <= point.getRadius()) {

                return recordRepo.addNewRecord(userId, payload.getPointId(), typeRecord);
            } else return null;
        } else return null;
    }
}
