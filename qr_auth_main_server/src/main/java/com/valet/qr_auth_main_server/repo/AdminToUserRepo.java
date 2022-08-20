package com.valet.qr_auth_main_server.repo;

import com.valet.qr_auth_main_server.model.AdminToUser;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AdminToUserRepo extends ReactiveCrudRepository<AdminToUser, Long> {
    @Query("SELECT * FROM admin_user WHERE admin_id = :id")
    Flux<AdminToUser> findAllByAdmin_id(@Param("id") Long id);
}
