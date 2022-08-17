package com.valet.qr_auth_main_server.repo;

import com.valet.qr_auth_main_server.model.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepo extends ReactiveCrudRepository<User, Long> {
    @Query("SELECT * FROM users WHERE users.email = :email")
    Mono<User> findByEmail(@Param("email") String username);

    @Modifying
    @Query("UPDATE users SET name = :username WHERE id = :id")
    Mono<Integer> changeNameById(@Param(value = "id") Long id, @Param("username") String username);

    @Modifying
    @Query("UPDATE users SET job_title = :jobTitle WHERE id = :id")
    Mono<Integer> changeJobTitleById(@Param(value = "jobTitle") String jobTitle,@Param(value = "id")  Long id);

    @Modifying
    @Query("UPDATE users SET password = :password WHERE id = :id")
    Mono<Integer> changePasswordById(@Param(value = "id") Long id,@Param(value = "password") String password);
}
