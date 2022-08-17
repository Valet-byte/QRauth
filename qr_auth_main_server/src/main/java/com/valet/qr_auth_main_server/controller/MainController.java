package com.valet.qr_auth_main_server.controller;

import com.valet.qr_auth_main_server.model.User;
import com.valet.qr_auth_main_server.service.interfaces.ActionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class MainController {

    private final ActionService actionService;

    @GetMapping("/login")
    public Mono<User> login(@AuthenticationPrincipal Mono<User> user){
        return user.map(a -> {a.setPassword("NONE"); return a;});
    }

    @PostMapping("/registration")
    public Mono<Boolean> registration(@RequestBody User user){
        return Mono.just(actionService.registration(user));
    }

}
