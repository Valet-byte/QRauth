package com.valet.qr_auth_main_server.service.interfaces;

import com.valet.qr_auth_main_server.model.Organization;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrganizationService {
    Flux<String> getAllOrganizationName();
    Mono<Void> deleteOrganization(Long organizationId);
    Mono<Void> deleteOrganization(String organizationName);

    Mono<Long> getIdByName(String value);
}
