package com.valet.qr_auth_main_server.service;


import com.valet.qr_auth_main_server.service.interfaces.GeographyService;
import org.springframework.stereotype.Service;

@Service
public class GeographyServiceImpl implements GeographyService {
    @Override
    public Double getDistance(Double x1,Double y1, Double x2, Double y2) {
        return getDis(x1, y1, x2, y2);
    }

    private double getDis(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);
        double earthRadius = 6371.01; //Kilometers
        return earthRadius * Math.acos(Math.sin(lat1)*Math.sin(lat2) + Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon1 - lon2));
    }
}
