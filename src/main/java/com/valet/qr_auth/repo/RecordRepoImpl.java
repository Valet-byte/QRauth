package com.valet.qr_auth.repo;

import com.valet.qr_auth.model.Person;
import com.valet.qr_auth.repo.mapper.PersonLowMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class RecordRepoImpl implements RecordRepo {

    private final NamedParameterJdbcTemplate template;
    private final PersonLowMapper mapper;

    @Override
    public List<Person> getAllRecordsByPointId(Long pointId) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("pointId", pointId);

        //List<Long> ids = template.query("SELECT user_id from ", source, (rs, rN) -> rs.getLong("user_id"));

        return template.query("SELECT public.person.*, public.record.* from public.record JOIN public.person ON person.id = record.user_id WHERE record.point_id = :pointId", source, mapper);
    }

    @Override
    public Long addNewRecord(Long userId, Long pointId, String typeRecord) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("pointId", pointId);
        source.addValue("userId", userId);
        source.addValue("typeRecord", typeRecord);
        source.addValue("date_time", Timestamp.valueOf(LocalDateTime.now().plusHours(3)));

        KeyHolder holder = new GeneratedKeyHolder();

        template.update("INSERT into public.record (user_id, point_id, date_time, type_record) VALUES " +
                "(:userId, :pointId, :date_time, :typeRecord)", source, holder);

        return (Long) Objects.requireNonNull(holder.getKeys()).get("id");
    }
}
