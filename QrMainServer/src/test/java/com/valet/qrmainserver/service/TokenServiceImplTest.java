package com.valet.qrmainserver.service;

import com.valet.qrmainserver.exception.TokenNotValidException;
import com.valet.qrmainserver.model.Payload;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TokenServiceImplTest {

    @Autowired
    private TokenServiceImpl tokenService;

    @Test
    @DisplayName("valid toke")
    void validTokenTest(){
        String token = tokenService.createToken(1.0, 1.0, 1, "id", "idOrg");

        Payload payload = tokenService.getPayload(token);
        assertAll("Return all param",
                () -> assertEquals(payload.getX(), 1.0, 0.001),
                () -> assertEquals(payload.getY(), 1.0, 0.001),
                () -> assertEquals(payload.getAdminId(), "id"),
                () -> assertEquals(payload.getOrganizationId(), "idOrg"));
    }

    @Test

    @DisplayName("Valid token from time")
    void validTokeFromTime(@Value("${validTime}") long validTime) throws InterruptedException {
        String token = tokenService.createToken(1.0, 1.0, 1, "id", "idOrg");

        Thread.sleep(validTime * 1500);
        assertThrows(TokenNotValidException.class, () -> {
            tokenService.getPayload(token);
        });

    }




}