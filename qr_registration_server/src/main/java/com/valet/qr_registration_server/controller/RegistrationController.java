package com.valet.qr_registration_server.controller;

import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.service.ActionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final ActionService actionService;

    @PostMapping("/doAction")
    public Mono<Boolean> registration(@RequestBody Action action){
        return actionService.doAction(action);
    }

}
