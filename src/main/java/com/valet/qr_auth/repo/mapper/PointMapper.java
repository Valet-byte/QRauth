package com.valet.qr_auth.repo.mapper;

import com.valet.qr_auth.model.Point;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PointMapper implements RowMapper<Point> {
    @Override
    public Point mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Point.builder()
                .id(rs.getLong("id"))
                .actualTime(rs.getTimestamp("actual_time").toLocalDateTime())
                .createTime(rs.getTimestamp("create_time").toLocalDateTime())
                .x(rs.getDouble("x"))
                .y(rs.getDouble("y"))
                .creatorId(rs.getLong("creator_id"))
                .radius(rs.getInt("radius"))
                .build();
    }
}
