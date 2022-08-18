package com.valet.qr_auth_main_server.repo;

import com.valet.qr_auth_main_server.model.Organization;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrganizationRepo extends ReactiveCrudRepository<Organization, Long> {
    @Query("SELECT name FROM organization")
    Flux<String> findName();

    @Query("SELECT id FROM organization WHERE name = :name")
    Mono<Long> findIdByName(@Param("name") String name);
}
