package com.valet.qr_auth_main_server.controller;

import com.valet.qr_auth_main_server.model.Organization;
import com.valet.qr_auth_main_server.model.Record;
import com.valet.qr_auth_main_server.model.User;
import com.valet.qr_auth_main_server.service.interfaces.ActionService;
import com.valet.qr_auth_main_server.service.interfaces.OrganizationService;
import com.valet.qr_auth_main_server.service.interfaces.RecordService;
import com.valet.qr_auth_main_server.service.interfaces.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class MainController {
    private final ActionService actionService;
    private final TokenService tokenService;
    private final RecordService recordService;
    private final OrganizationService organizationService;

    @GetMapping("/login")
    public Mono<User> login(@AuthenticationPrincipal Mono<User> user) {
        return user.map(a -> {
            a.setPassword("NONE");
            return a;
        });
    }

    @PostMapping("/registration")
    public Mono<Boolean> registration(@RequestBody User user) {
        System.out.println(user);
        return actionService.registration(user);
    }

    @GetMapping("/getToken")
    public Mono<String> getToken(@RequestParam double x, @RequestParam double y, @RequestParam int radius, @AuthenticationPrincipal User user) {
        return tokenService.createToken(x, y, radius, user.getId(), user.getOrganization().getId());
    }

    @PostMapping("/setRecord")
    public Mono<Record> setRecord(@RequestParam double x, @RequestParam double y, @RequestParam String token, @AuthenticationPrincipal User user){
        System.out.println(user);
        return recordService.addRecord(x, y, token, user);
    }

    @PostMapping("/createOrganization")
    public Mono<Organization> createOrganization(@RequestParam String organizationName, @AuthenticationPrincipal User user){
        return organizationService.createOrganization(user.getId(), organizationName);
    }

    @GetMapping("/getAllOrganization")
    public Flux<String> getAllOrganization(){
        return organizationService.getAllOrganizationName();
    }

    @PutMapping("/addAdmin")
    public Mono<Boolean> addAdmin(
            @RequestParam Long userId,
            @AuthenticationPrincipal User user
    ){

    }
}
