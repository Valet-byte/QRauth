package com.valet.qr_auth.repo;

import com.valet.qr_auth.model.Point;
import com.valet.qr_auth.repo.mapper.PointMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
@AllArgsConstructor
public class PointRepoImpl implements PointRepo {

    private final NamedParameterJdbcTemplate template;
    private final PointMapper mapper;

    @Override
    public Point findByActiveTime(Long userId, Long activeTime) {
        return null;
    }

    @Override
    public Point save(Point point) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("creator_id", point.getCreatorId());
        source.addValue("x", point.getX());
        source.addValue("y", point.getY());
        source.addValue("radius", point.getRadius());
        source.addValue("create_time", Timestamp.valueOf(point.getCreateTime().plusHours(3)));
        source.addValue("actual_time", Timestamp.valueOf(point.getActualTime().plusHours(3)));

        KeyHolder holder = new GeneratedKeyHolder();

        template.update("INSERT into public.point (creator_id, x, y, radius, create_time, actual_time) values " +
                "(:creator_id, :x, :y, :radius, :create_time, :actual_time)", source, holder);

        point.setId((Long) holder.getKeys().get("id"));
        return point;

    }

    @Override
    public boolean existPoint(Long idUser, Long idPoint) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("user_id", idUser);
        source.addValue("point_id", idPoint);

        return Boolean.TRUE.equals(template.queryForObject("SELECT exists(select * from public.point WHERE id = :point_id AND creator_id = :user_id)", source, Boolean.class));
    }

    @Override
    public Point findById(Long id) {

        System.out.println("---------------- " + id + " ---------------------------");

        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);

        return template.queryForObject("SELECT * from public.point WHERE id = :id", source, mapper);
    }

    @Override
    public List<Point> getAllPoint(Long id) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);

        return template.query("SELECT * from public.point WHERE creator_id = :id", source, mapper);
    }
}
