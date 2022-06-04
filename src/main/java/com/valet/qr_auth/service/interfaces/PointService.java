package com.valet.qr_auth.service.interfaces;

import com.valet.qr_auth.model.Point;

import java.util.List;

public interface PointService {
    Point generatePoint(Point point);
    List<Point> getAllPoint(Long id);

    Point findById(Long pointId, Long userId);
}
