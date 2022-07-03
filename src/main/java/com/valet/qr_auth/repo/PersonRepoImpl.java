package com.valet.qr_auth.repo;

import com.valet.qr_auth.model.Person;
import com.valet.qr_auth.repo.mapper.PersonMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Objects;

@Repository
@AllArgsConstructor
public class PersonRepoImpl implements PersonRepo {

    private final NamedParameterJdbcTemplate template;
    private final PersonMapper mapper;

    @Override
    public Person findByName(String name) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("name", name);

        return template.queryForObject("SELECT * from public.person WHERE name = :name", source, mapper);
    }

    @Override
    public Person findByPhone(String phone) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("phone", phone);

        return template.queryForObject("SELECT * from public.person WHERE phone = :phone", source, mapper);
    }

    @Override
    public Person save(Person person) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("name", person.getName());
        source.addValue("password", person.getPassword());
        source.addValue("roles", createSqlArray("text", person.getRoles().toArray()));
        source.addValue("organization", person.getOrganization());
        source.addValue("job_title", person.getJobTitle());
        source.addValue("phone", person.getPhone());

        KeyHolder holder = new GeneratedKeyHolder();

        template.update("INSERT into public.person (name, password, job_title, organization, roles, phone) " +
                "values (:name, :password, :job_title, :organization, :roles, :phone)", source, holder);

        person.setId((Long) Objects.requireNonNull(holder.getKeys()).get("id"));
        return person;
    }

    @Override
    public Person findById(Long id) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        return template.queryForObject("SELECT * from public.person WHERE id = :id", source, mapper);
    }

    @Override
    public boolean existUser(String phone) {

        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("phone", phone);
        Long id = template.queryForObject("select id from public.person WHERE phone = :phone", source, Long.TYPE);
        return id != null;
    }

    @Override
    public String changeOrganization(Long id, String organization) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        source.addValue("organization", organization);

        try {
            KeyHolder holder = new GeneratedKeyHolder();
            template.update("UPDATE public.person SET organization = :organization " +
                    "WHERE id = :id", source, holder);

            return (String) holder.getKeys().get("organization");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String changePhone(Long id, String phone) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        source.addValue("phone", phone);

        try {
            KeyHolder holder = new GeneratedKeyHolder();
            template.update("UPDATE public.person SET phone = :phone " +
                    "WHERE id = :id", source, holder);

            return (String) holder.getKeys().get("phone");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String changeName(Long id, String name) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        source.addValue("name", name);

        try {
            KeyHolder holder = new GeneratedKeyHolder();
            template.update("UPDATE public.person SET name = :name " +
                    "WHERE id = :id", source, holder);

            return (String) holder.getKeys().get("name");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("userId", id);

        try {
            template.update("DELETE FROM public.person WHERE id = :userId", source);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changePassword(Long id, String encodePass) {
        final MapSqlParameterSource source = new MapSqlParameterSource();
        source.addValue("id", id);
        source.addValue("pass", encodePass);

        try {
            KeyHolder holder = new GeneratedKeyHolder();
            template.update("UPDATE public.person SET password = :pass " +
                    "WHERE id = :id", source, holder);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private java.sql.Array createSqlArray(String type, Object... data){
        java.sql.Array intArray = null;
        try {
            intArray = Objects.requireNonNull(template.getJdbcTemplate().getDataSource()).getConnection().createArrayOf(type, data);
        } catch (SQLException ignore) {
        }
        return intArray;
    }
}
