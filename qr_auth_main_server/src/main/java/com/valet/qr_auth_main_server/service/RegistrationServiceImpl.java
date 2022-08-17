package com.valet.qr_auth_main_server.service;

import com.valet.qr_auth_main_server.model.User;
import com.valet.qr_auth_main_server.service.interfaces.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final WebClient.Builder builder;

    @Override
    public Mono<User> registration(User user) {
        return builder
                .build().post()
                .uri("http://REGISTRATION/registration")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class).log();
    }

    @Override
    public Mono<Void> deleteUser(Long id) {
        return null;
    }
}
