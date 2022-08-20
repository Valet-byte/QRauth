package com.valet.qr_registration_server.repo;

import com.valet.qr_registration_server.model.AdminToUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AdminToUserRepo extends ReactiveCrudRepository<AdminToUser, Long> {
}
