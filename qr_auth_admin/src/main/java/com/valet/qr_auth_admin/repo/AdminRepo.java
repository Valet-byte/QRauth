package com.valet.qr_auth_admin.repo;

import com.valet.qr_auth_admin.model.Admin;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AdminRepo extends ReactiveCrudRepository<Admin, Long> {
    Mono<Admin> findByEmail(String email);
    @Query("UPDATE admin_user SET name = :username WHERE id = :id")
    @Modifying
    Mono<Integer> changeNameById(@Param(value = "id") Long id, @Param("username") String username);

    @Modifying
    @Query("update admin_user set job_title = :jobTitle where id = :id")
    Mono<Integer> changeJobTitleById(@Param(value = "jobTitle") String jobTitle,@Param(value = "id")  Long id);

    @Query("UPDATE admin_user SET password = :password WHERE id = :id")
    @Modifying
    Mono<Integer> changePasswordById(@Param(value = "id") Long id,@Param(value = "password") String password);
}
