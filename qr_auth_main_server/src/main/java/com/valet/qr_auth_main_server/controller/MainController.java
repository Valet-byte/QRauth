package com.valet.qr_auth_main_server.controller;

import com.valet.qr_auth_main_server.model.AdminToUser;
import com.valet.qr_auth_main_server.model.Record;
import com.valet.qr_auth_main_server.model.User;
import com.valet.qr_auth_main_server.model.changeAction.Action;
import com.valet.qr_auth_main_server.model.changeAction.ActionType;
import com.valet.qr_auth_main_server.model.dto.UserDTO;
import com.valet.qr_auth_main_server.service.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class MainController {
    private final TokenService tokenService;
    private final RecordService recordService;
    private final OrganizationService organizationService;
    private final ActionService actionService;
    private final CommandService commandService;

    @GetMapping("/login")
    public Mono<User> login(@AuthenticationPrincipal Mono<User> user) {
        return user.map(a -> {
            a.setPassword("NONE");
            return a;
        });
    }

    @GetMapping("/getToken")
    public Mono<String> getToken(@RequestParam double x, @RequestParam double y, @RequestParam int radius, @AuthenticationPrincipal User user) {
        return tokenService.createToken(x, y, radius, user.getId(), user.getOrganization().getId());
    }

    @PostMapping("/setRecord")
    public Mono<Record> setRecord(@RequestParam double x,
                                  @RequestParam double y,
                                  @RequestParam String token,
                                  @AuthenticationPrincipal User user){
        System.out.println(user);
        return recordService.addRecord(x, y, token, user);
    }

    @PostMapping("/createOrganization")
    public Mono<Boolean> createOrganization(@RequestParam String organizationName, @AuthenticationPrincipal User user){
        Action action = actionService.createActionNotRegistrationInRedis(user.getId(), organizationName, ActionType.CREATE_ORGANIZATION);
        return actionService.doAction(action);
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
        AdminToUser adminToUser = new AdminToUser(null, user.getId(), userId);
        return actionService.doAction(actionService.createActionNotRegistrationInRedis(user.getId(), adminToUser, ActionType.ADD_IN_COMMAND_AND_MAKE_ADMIN));
    }

    @PutMapping("/addToMyCommand")
    public Mono<Boolean> addToMyCommand(
            @RequestParam Long userId,
            @AuthenticationPrincipal User user
    ){
        AdminToUser adminToUser = new AdminToUser(null, user.getId(), userId);

        return actionService.doAction(actionService.createActionNotRegistrationInRedis(user.getId(), adminToUser, ActionType.ADD_IN_COMMAND));
    }

    @GetMapping("/getMyCommand")
    public Flux<UserDTO> getMyCommand(
            @AuthenticationPrincipal User user
    ){
        try {
            return commandService.getMyCommand(user.getId());
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
