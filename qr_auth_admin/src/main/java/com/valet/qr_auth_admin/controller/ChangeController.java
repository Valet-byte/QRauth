package com.valet.qr_auth_admin.controller;

import com.valet.qr_auth_admin.model.Admin;
import com.valet.qr_auth_admin.service.interfaces.ActionService;
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
            @AuthenticationPrincipal Admin admin){
        return Mono.just(actionService.changeName(admin.getId(), admin.getEmail(), username));
    }

    @PutMapping("/changePassword")
    public Mono<Boolean> changePassword(
            @RequestParam String password,
            @AuthenticationPrincipal Admin admin){
        return Mono.just(actionService.changePassword(admin.getId(), admin.getEmail(), password));
    }

    @PutMapping("/changeJobTitle")
    public Mono<Boolean> changeJobTitle(
            @RequestParam String jobTitle,
            @AuthenticationPrincipal Admin admin){
        return Mono.just(actionService.changeJobTitle(admin.getId(), admin.getEmail(), jobTitle));
    }

    @GetMapping("/do/{actionId}")
    public Mono<Boolean> doAction(@PathVariable String actionId){
        return Mono.just(actionService.doAction(actionId));
    }
}
