package com.valet.qrmainserver.controller;

import com.valet.qrmainserver.model.Organization;
import com.valet.qrmainserver.model.Team;
import com.valet.qrmainserver.model.TeamToUser;
import com.valet.qrmainserver.model.User;
import com.valet.qrmainserver.model.action.ActionType;
import com.valet.qrmainserver.model.dto.UserDTO;
import com.valet.qrmainserver.service.interfaces.ActionService;
import com.valet.qrmainserver.service.interfaces.TeamService;
import com.valet.qrmainserver.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.loader.ResourceEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class ChangeController {

    private final ActionService actionService;
    private final PasswordEncoder encoder;
    private final UserService userService;

    private final TeamService teamService;


    @GetMapping("/do/{actionId}")
    public boolean doAction(@PathVariable String actionId){
        return actionService.doAction(actionId);

    }

    @PutMapping("/changeName")
    public boolean changeName(
            @RequestParam String username,
            @AuthenticationPrincipal User user){
        if (user.getName().equals(username)){
            return true;
        }
        if (username.split(" ").length == 3){
            return actionService.createAndSendAction(user.getId(), username, ActionType.CHANGE_USERNAME, user.getEmail(), user.getUsername());
        } else {
            return false;
        }

    }

    @PutMapping("/changePassword")
    public boolean changePassword(
            @RequestParam String password,
            @AuthenticationPrincipal User user){
        return actionService.createAndSendAction(user.getId(), encoder.encode(password), ActionType.CHANGE_PASSWORD, user.getEmail(), user.getUsername());
    }

    @PutMapping("/changeJobTitle")
    public boolean changeJobTitle(
            @RequestParam String jobTitle,
            @AuthenticationPrincipal User user){
        return actionService.createAndSendAction(user.getId(), jobTitle, ActionType.CHANGE_JOB_TITLE, user.getEmail(), user.getUsername());
    }

    @PutMapping("/changeOrganization")
    public boolean changeOrganization(
            @RequestParam(defaultValue = "none") String userId,
            @AuthenticationPrincipal User user){
        if (!userId.equals("none")){
            UserDTO dto = userService.getDtoById(userId);
            return actionService.createAndSendAction(userId, user.getOrganizationId(), ActionType.CHANGE_ORGANIZATION, dto.getEmail(), dto.getName(), user.getName());

        }
        return false;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody User user) {
        if (userService.countByEmail(user.getEmail()) == 0){
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRole("USER");

            return ResponseEntity.ok(actionService.createAndSendAction(null, user, ActionType.REGISTRATION, user.getEmail(), user.getUsername()));
        }
        return ResponseEntity.badRequest().body("Пользователь с таким email уже есть!");
    }

    @DeleteMapping("/deleteUser")
    public boolean deleteUser(@AuthenticationPrincipal User user){
        return actionService.createAndSendAction(user.getId(), user, ActionType.DELETE_USER, user.getEmail(), user.getUsername());
    }

    @DeleteMapping("/deleteOrganization")
    public boolean deleteOrganization(@AuthenticationPrincipal User user){
        return actionService.createAndSendAction(user.getId(), user, ActionType.DELETE_ORGANIZATION, user.getEmail(), user.getUsername());
    }

    @PostMapping("/changeOwnerOrganization")
    public boolean changeOwnerOrganization(@AuthenticationPrincipal User user,
                                           @RequestParam String userId){
        return false;
    }

    @PostMapping("/createOrganization")
    public boolean createOrganization(@AuthenticationPrincipal User user,
                                      @RequestParam String organizationName){
        Organization organization = new Organization(null, organizationName, user.getId(), null, LocalDateTime.now());
        return actionService.createAndSendAction(user.getId(), organization, ActionType.CREATE_ORGANIZATION, user.getEmail(), user.getUsername());
    }

    @PostMapping("/createTeam")
    public boolean createTeam(@AuthenticationPrincipal User user,
                              @RequestBody Team team){
        if (team.getAdminId() != null){
            UserDTO dto = userService.getDtoById(team.getAdminId());

            if (team.getName() != null &&
                    team.getAdminId() != null &&
                    dto.getOrganizationId().equals(user.getOrganizationId())){
                team.setOrganizationId(user.getOrganizationId());
                return actionService.doAction(actionService.createAction(user.getId(), team, ActionType.CREATE_TEAM));
            }
        }
        return false;

    }

    @PutMapping("/addToTeam")
    public ResponseEntity<?> addToTeam(
            @RequestParam String userId,
            @AuthenticationPrincipal User user){
            if (!teamService.existUserInAnyTeam(userId)){
                String teamId = teamService.getIdByLeadId(user.getId());
                TeamToUser teamToUser = new TeamToUser(null, teamId, userId);
                return ResponseEntity.ok().body(actionService.doAction(actionService.createAction(user.getId(), teamToUser, ActionType.ADD_IN_TEAM)));
            }

            return ResponseEntity.badRequest().body("Пользователь уже состоит в команде!");
    }


}

