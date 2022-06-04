package com.valet.qr_auth.repo;

import com.valet.qr_auth.model.Point;

import java.util.List;

public interface PointRepo {
    Point findByActiveTime(Long userId, Long activeTime);
    Point save(Point point);
    boolean existPoint(Long idUser, Long idPoint);
    Point findById(Long id);

    List<Point> getAllPoint(Long id);
}
