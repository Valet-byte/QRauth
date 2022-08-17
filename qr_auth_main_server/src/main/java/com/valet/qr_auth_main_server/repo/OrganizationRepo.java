package com.valet.qr_auth_main_server.repo;

import com.valet.qr_auth_main_server.model.Organization;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrganizationRepo extends ReactiveCrudRepository<Organization, Long> {
}
