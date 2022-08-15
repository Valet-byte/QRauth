package com.valet.qr_registration_server.controller;

import com.valet.qr_registration_server.model.User;
import com.valet.qr_registration_server.serice.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/registration")
    public Mono<User> registration(@RequestBody User user){
        return userService.registration(user);
    }
}
