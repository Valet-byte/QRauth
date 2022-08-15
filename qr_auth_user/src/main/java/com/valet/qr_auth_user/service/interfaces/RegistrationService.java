package com.valet.qr_auth_user.service.interfaces;

import com.valet.qr_auth_user.model.User;
import reactor.core.publisher.Mono;

public interface RegistrationService {
    public Mono<User> registration(User user);
}
