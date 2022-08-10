package com.valet.qr_auth_user.service.interfaces;

import com.valet.qr_auth_user.model.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> registration(User admin);
    Mono<Void> delete(User admin);
}
