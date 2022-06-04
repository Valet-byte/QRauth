package com.valet.qr_auth.repo.mapper;

import com.valet.qr_auth.model.Record;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RecordMapper implements RowMapper<Record> {
    @Override
    public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Record.builder()
                .id(rs.getLong("id"))
                .userId(rs.getLong("user_id"))
                .pointId(rs.getLong("point_id"))
                .dateTime(rs.getTimestamp("date_time").toLocalDateTime())
                .typeRecord(rs.getString("type_record"))
                .build();
    }
}
