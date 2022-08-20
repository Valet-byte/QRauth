package com.valet.qr_auth_main_server.repo;

import com.valet.qr_auth_main_server.model.User;
import com.valet.qr_auth_main_server.model.dto.UserDTO;
import io.lettuce.core.dynamic.annotation.Param;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepo extends ReactiveCrudRepository<User, Long> {
    @Query("SELECT * FROM users WHERE users.email = :email")
    Mono<User> findByEmail(@Param("email") String username);

    @Query("SELECT EXISTS (SELECT id FROM users WHERE email = :email)")
    Mono<Boolean> existUser(@Param("email") String email);

}
