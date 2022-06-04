package com.valet.qr_auth.service;

import com.valet.qr_auth.model.Point;
import com.valet.qr_auth.repo.PointRepo;
import com.valet.qr_auth.service.interfaces.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepo pointRepo;

    @Override
    public Point generatePoint(Point point) {

        point.setCreateTime(LocalDateTime.now().plusHours(3));

        return pointRepo.save(point);
    }

    @Override
    public List<Point> getAllPoint(Long id) {
        return pointRepo.getAllPoint(id);
    }

    @Override
    @Cacheable("point")
    public Point findById(Long pointId, Long userId) {
        Point point = pointRepo.findById(pointId);
        if (point.getCreatorId().equals(userId)){
            return point;
        } else {
            return null;
        }
    }
}
