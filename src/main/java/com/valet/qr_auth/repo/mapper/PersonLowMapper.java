package com.valet.qr_auth.repo.mapper;

import com.valet.qr_auth.model.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PersonLowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Person.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .phone(rs.getString("phone"))
                .jobTitle(rs.getString("job_title"))
                .time(rs.getTimestamp("date_time").toLocalDateTime())
                .build();
    }
}
