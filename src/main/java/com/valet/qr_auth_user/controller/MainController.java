package com.valet.qr_auth_user.controller;

import com.valet.qr_auth_user.model.Record;
import com.valet.qr_auth_user.model.User;
import com.valet.qr_auth_user.service.interfaces.RecordService;
import com.valet.qr_auth_user.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class MainController {

    private final UserService adminService;
    private final RecordService recordService;

    @PostMapping("/registration")
    public Mono<ResponseEntity<?>> registration(@RequestBody User user){
        try {
            return adminService.registration(user)
                    .onErrorReturn(User.builder().build())
                    .map(a -> {
                        if (a.getName() == null && a.getEmail() == null){
                            return new ResponseEntity<>("Что-то пошло не так", HttpStatus.BAD_REQUEST);
                        }
                        return new ResponseEntity<>(a, HttpStatus.CREATED);
                    });
        } catch (Exception e){
            e.printStackTrace();
            return Mono.error(new Throwable("Что-то пошло не так"));
        }

    }

    @GetMapping("/login")
    public Mono<User> login(@AuthenticationPrincipal Mono<User> user){
        return user.map(a -> {a.setPassword("NONE"); return a;});
    }

    @PostMapping("/setRecord")
    public Mono<Record> setRecord(
                            @RequestParam double x,
                            @RequestParam double y,
                            @RequestParam String token,
                            @AuthenticationPrincipal User user){
        System.out.println(user);
        return recordService.addRecord(x, y, token, user);
    }
}
