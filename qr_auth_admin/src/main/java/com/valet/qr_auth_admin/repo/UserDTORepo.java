package com.valet.qr_auth_admin.repo;

import com.valet.qr_auth_admin.model.DTO.UserDTO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserDTORepo extends ReactiveCrudRepository<UserDTO, Long> {
}
