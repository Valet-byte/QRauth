package com.valet.qr_auth_main_server.service.interfaces;


import com.valet.qr_auth_main_server.model.User;
import reactor.core.publisher.Mono;

public interface RegistrationService {
    public Mono<User> registration(User user);
    public Mono<Void> deleteUser(Long id);
}
