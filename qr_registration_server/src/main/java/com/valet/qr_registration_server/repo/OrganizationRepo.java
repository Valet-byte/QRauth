package com.valet.qr_registration_server.repo;

import com.valet.qr_registration_server.model.Organization;
import lombok.NonNull;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface OrganizationRepo extends ReactiveCrudRepository<Organization, Long> {
    Mono<Boolean> existsById(@NonNull Long id);
}
