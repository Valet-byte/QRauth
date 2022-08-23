package com.valet.qr_registration_server.repo;

import com.valet.qr_registration_server.model.Organization;
import lombok.NonNull;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface OrganizationRepo extends ReactiveCrudRepository<Organization, Long> {
    Mono<Boolean> existsById(@NonNull Long id);
    @Modifying
    Mono<Void> deleteByCreatorId(Long creatorId);

    @Query("SELECT id FROM organization WHERE name = :name")
    Mono<Long> findIdByName(@Param("name") String name);
}
