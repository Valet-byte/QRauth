package com.valet.qr_auth_main_server.controller;

import com.valet.qr_auth_main_server.service.interfaces.ActionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class ChangeController {

    private final ActionService actionService;

    @GetMapping("/do/{actionId}")
    public Mono<Boolean> doAction(@PathVariable String actionId){
        return Mono.just(actionService.doAction(actionId));
    }
}
