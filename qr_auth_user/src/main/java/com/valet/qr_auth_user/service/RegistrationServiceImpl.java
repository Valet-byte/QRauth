package com.valet.qr_auth_user.service;

import com.valet.qr_auth_user.model.User;
import com.valet.qr_auth_user.service.interfaces.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final WebClient.Builder builder;
    private final PasswordEncoder encoder;

    @Override
    public Mono<User> registration(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return builder
                .build().post()
                .uri("http://REGISTRATION/registration")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class);
    }
}
