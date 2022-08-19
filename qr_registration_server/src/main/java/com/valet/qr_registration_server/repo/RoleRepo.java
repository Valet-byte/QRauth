package com.valet.qr_registration_server.repo;

import com.valet.qr_registration_server.model.Role;
import lombok.NonNull;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface RoleRepo extends ReactiveCrudRepository<Role, Long> {
    Mono<Boolean> existsById(@NonNull Long id);
}
