package com.valet.qr_auth_main_server.controller;

import com.valet.qr_auth_main_server.model.User;
import com.valet.qr_auth_main_server.model.changeAction.ActionType;
import com.valet.qr_auth_main_server.service.interfaces.ActionService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class ChangeController {

    private final ActionService actionService;
    private final PasswordEncoder encoder;

    @GetMapping("/do/{actionId}")
    public Mono<Boolean> doAction(@PathVariable String actionId){
        return actionService.doAction(actionId);

    }

    @PutMapping("/changeName")
    public Mono<Boolean> changeName(
            @RequestParam String username,
            @AuthenticationPrincipal User user){
        return Mono.just(actionService.createAndSendAction(user.getId(), username, ActionType.CHANGE_USERNAME, user.getEmail()));
    }

    @PutMapping("/changePassword")
    public Mono<Boolean> changePassword(
            @RequestParam String password,
            @AuthenticationPrincipal User user){
        return Mono.just(actionService.createAndSendAction(user.getId(), encoder.encode(password), ActionType.CHANGE_PASSWORD, user.getEmail()));
    }

    @PutMapping("/changeJobTitle")
    public Mono<Boolean> changeJobTitle(
            @RequestParam String jobTitle,
            @AuthenticationPrincipal User user){
        return Mono.just(actionService.createAndSendAction(user.getId(), jobTitle, ActionType.CHANGE_JOB_TITLE, user.getEmail()));
    }

    @PutMapping("/changeOrganization")
    public Mono<Boolean> changeOrganization(
            @RequestParam String organizationName,
            @AuthenticationPrincipal User user){
        return Mono.just(actionService.createAndSendAction(user.getId(), organizationName, ActionType.CHANGE_ORGANIZATION, user.getEmail()));
    }

    @PostMapping("/registration")
    public Mono<Boolean> registration(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return Mono.just(actionService.createAndSendAction(null, user, ActionType.REGISTRATION, user.getEmail()));
    }
}
