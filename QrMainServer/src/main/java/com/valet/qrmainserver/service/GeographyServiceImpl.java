package com.valet.qrmainserver.service;

import com.valet.qrmainserver.service.interfaces.GeographyService;
import org.springframework.stereotype.Service;

@Service
public class GeographyServiceImpl implements GeographyService {
    @Override
    public Double getDistance(Double x1,Double y1, Double x2, Double y2) {
        x1 = Math.toRadians(x1);
        y1 = Math.toRadians(y1);
        x2 = Math.toRadians(x2);
        y2 = Math.toRadians(y2);
        double earthRadius = 6371.01; //Kilometers
        return (earthRadius * Math.acos(Math.sin(x1)*Math.sin(x2) + Math.cos(x1)*Math.cos(x2)*Math.cos(y1 - y2))) * 1000;
    }

}
