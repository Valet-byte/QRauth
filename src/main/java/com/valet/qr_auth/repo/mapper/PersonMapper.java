package com.valet.qr_auth.repo.mapper;

import com.valet.qr_auth.model.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Person.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .jobTitle(rs.getString("job_title"))
                .phone(rs.getString("phone"))
                .organization(rs.getString("organization"))
                .password(rs.getString("password"))
                .roles(List.of((String[]) (rs.getArray("roles").getArray())))
                .build();
    }
}
