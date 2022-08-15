package com.valet.qr_auth_user.controller;

import com.valet.qr_auth_user.model.User;
import com.valet.qr_auth_user.service.interfaces.ActionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class ChangeController {

    private final ActionService actionService;

    @PutMapping("/changeName")
    public Mono<Boolean> changeName(
            @RequestParam String username,
            @AuthenticationPrincipal User user){
        return Mono.just(actionService.changeName(user.getId(), user.getEmail(), username));
    }

    @PutMapping("/changePassword")
    public Mono<Boolean> changePassword(
            @RequestParam String password,
            @AuthenticationPrincipal User user){
        return Mono.just(actionService.changePassword(user.getId(), user.getEmail(), password));
    }

    @PutMapping("/changeJobTitle")
    public Mono<Boolean> changeJobTitle(
            @RequestParam String jobTitle,
            @AuthenticationPrincipal User user){
        return Mono.just(actionService.changeJobTitle(user.getId(), user.getEmail(), jobTitle));
    }

    @GetMapping("/do/{actionId}")
    public Mono<Boolean> doAction(@PathVariable String actionId){
        return Mono.just(actionService.doAction(actionId));
    }
}
