package com.valet.qr_registration_server.repo;

import com.valet.qr_registration_server.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepo extends ReactiveCrudRepository<User, Long> {
    @Query("INSERT INTO users (name, password, job_title, phone, organization_id, email, role_id) VALUES" +
            " (:name, :password, :job_title, :phone, (SELECT id FROM organization WHERE name = :organization_name), :email, " +
            "(SELECT id FROM roles WHERE name = :role_name))")
    Mono<User> registration(@Param("name") String name, @Param("password") String password, @Param("email") String email, @Param("phone") String phone,
                            @Param("organization_name") String organizationName, @Param("role_name") String roleName, @Param("job_title") String job_title);
}
