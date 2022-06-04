package com.valet.qr_auth.service;

import com.valet.qr_auth.model.Point;
import com.valet.qr_auth.repo.PointRepo;
import com.valet.qr_auth.service.interfaces.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepo pointRepo;


    @Override
    public Point getPoint(Long personId, Long activeTime) {
        return null;
    }

    @Override
    public Point getPoint(Long personId) {
        return Point.builder().actualTime(LocalDateTime.now().plusHours(3)).build();
    }

    @Override
    public Point generatePoint(Point point) {

        point.setCreateTime(LocalDateTime.now().plusHours(3));

        return pointRepo.save(point);
    }

    @Override
    public List<Point> getAllPoint(Long id) {
        return pointRepo.getAllPoint(id);
    }
}
