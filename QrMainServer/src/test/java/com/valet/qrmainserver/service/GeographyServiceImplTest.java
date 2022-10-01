package com.valet.qrmainserver.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GeographyServiceImplTest {

    @Autowired
    private GeographyServiceImpl geographyService;

    @Test
    @DisplayName("Test distance")
    void testDistance(){
        assertEquals(geographyService.getDistance(44.996259, 41.925695, 44.997738, 41.926000), 166.19, 0.01);
    }
}