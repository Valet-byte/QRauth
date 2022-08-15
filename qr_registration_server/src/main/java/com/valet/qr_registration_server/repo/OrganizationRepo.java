package com.valet.qr_registration_server.repo;

import com.valet.qr_registration_server.model.Organization;
import org.springframework.data.repository.CrudRepository;
import reactor.core.publisher.Mono;

public interface OrganizationRepo extends CrudRepository<Organization, Long> {
    Mono<Organization> findByName( String name);
}
