package com.valet.qr_registration_server.repo;

import com.valet.qr_registration_server.model.User;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepo extends ReactiveCrudRepository<User, Long> {
    @Query("INSERT INTO users (name, password, job_title, phone, email, role_id) VALUES" +
            " (:name, :password, :job_title, :phone, :email, " +
            "(SELECT id FROM roles WHERE name = :role_name))")
    Mono<User> registration(@Param("name") String name, @Param("password") String password, @Param("email") String email, @Param("phone") String phone,
                             @Param("role_name") String roleName, @Param("job_title") String job_title);

    @Modifying
    @Query("UPDATE users SET name = :username WHERE id = :id")
    Mono<Integer> changeNameById(@Param(value = "id") Long id, @Param("username") String username);

    @Modifying
    @Query("UPDATE users SET job_title = :jobTitle WHERE id = :id")
    Mono<Integer> changeJobTitleById(@Param(value = "id")  Long id, @Param(value = "jobTitle") String jobTitle);

    @Modifying
    @Query("UPDATE users SET password = :password WHERE id = :id")
    Mono<Integer> changePasswordById(@Param(value = "id") Long id,@Param(value = "password") String password);

    @Modifying
    @Query("UPDATE users SET role_id = :roleId WHERE id = :userId")
    Mono<Integer> changeRoleUser(@Param("userId") Long userId, @Param("roleId") Long roleId);
    @Modifying
    @Query("UPDATE users SET organization_id = :organization_id WHERE id = :userId")
    Mono<Boolean> changeOrganizationUser(@Param("userId") Long userId, @Param("organization_id") Long orgId);
    @Query("SELECT id from users WHERE organization_id = :oId")
    Flux<Long> findIdByOrganizationId(@Param("oId") Long organizationId);

    @Modifying
    @Query("UPDATE users SET organization_id = null WHERE organization_id = :id")
    Mono<Boolean> setNullOrgIdAllUsersByIds(@Param("id") Long orgId );
}
