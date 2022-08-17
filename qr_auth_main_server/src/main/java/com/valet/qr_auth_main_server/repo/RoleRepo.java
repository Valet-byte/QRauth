package com.valet.qr_auth_main_server.repo;

import com.valet.qr_auth_main_server.model.Role;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RoleRepo extends ReactiveCrudRepository<Role, Long> {
}
