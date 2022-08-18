package com.valet.qr_registration_server.serice;

import com.valet.qr_registration_server.model.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> registration(User user);
    Mono<Void> delete(Long id);
    Mono<Void> changeRoleUser(Long userId, Long roleId);
    Mono<Void> changeOrganizationUser(Long userId, Long orgId);
}
