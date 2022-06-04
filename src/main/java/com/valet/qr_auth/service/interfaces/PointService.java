package com.valet.qr_auth.service.interfaces;

import com.valet.qr_auth.model.Point;

import java.util.List;

public interface PointService {
    Point getPoint(Long personId, Long activeTime);
    Point getPoint(Long personId);
    Point generatePoint(Point point);

    List<Point> getAllPoint(Long id);
}
