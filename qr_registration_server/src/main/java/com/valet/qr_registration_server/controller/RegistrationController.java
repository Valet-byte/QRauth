package com.valet.qr_registration_server.controller;

import com.valet.qr_registration_server.model.User;
import com.valet.qr_registration_server.serice.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/registration")
    public Mono<User> registration(@RequestBody User user){
        System.out.println(user);
        return userService.registration(user);
    }

    @DeleteMapping("/deleteUser")
    public Mono<Void> deleteUser(@RequestParam Long userId){
        return userService.delete(userId);
    }

    @PutMapping("/changeRoleUser")
    public Mono<Void> changeRoleUser(
            @RequestParam Long userId,
            @RequestParam Long roleId
    ){
        return userService.changeRoleUser(userId, roleId);
    }

    @PutMapping("/changeOrganizationUser")
    public Mono<Void> changeOrganizationUser(
            @RequestParam Long userId,
            @RequestParam Long orgId
    ){
        return userService.changeOrganizationUser(userId, orgId);
    }
}
