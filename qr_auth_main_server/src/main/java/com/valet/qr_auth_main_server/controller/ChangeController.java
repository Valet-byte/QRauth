package com.valet.qr_auth_main_server.controller;

import com.valet.qr_auth_main_server.model.User;
import com.valet.qr_auth_main_server.service.interfaces.ActionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class ChangeController {

    private final ActionService actionService;

    @GetMapping("/do/{actionId}")
    public Mono<Boolean> doAction(@PathVariable String actionId){
        return Mono.just(actionService.doAction(actionId));
    }

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

    @PutMapping("/changeOrganization")
    public Mono<Boolean> changeOrganization(
            @RequestParam String organizationName,
            @AuthenticationPrincipal User user){
        return Mono.just(actionService.changeOrganization(user.getId(), user.getEmail(), organizationName));
    }
}
