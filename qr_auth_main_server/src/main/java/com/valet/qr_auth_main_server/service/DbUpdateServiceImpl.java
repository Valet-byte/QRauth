package com.valet.qr_auth_main_server.service;

import com.valet.qr_auth_main_server.model.changeAction.Action;
import com.valet.qr_auth_main_server.service.interfaces.DbUpdateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DbUpdateServiceImpl implements DbUpdateService {

    private final WebClient.Builder builder;

    @Override
    public Mono<Boolean> doAction(Action action) {

        return builder.build()
                .post()
                .uri("http://DBSERVER/doAction")
                .bodyValue(action)
                .retrieve()
                .bodyToMono(Boolean.class);
    }
}
