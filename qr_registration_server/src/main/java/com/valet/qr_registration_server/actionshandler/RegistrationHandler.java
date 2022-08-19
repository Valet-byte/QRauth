package com.valet.qr_registration_server.actionshandler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.qr_registration_server.model.User;
import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.model.changeAction.ActionType;
import com.valet.qr_registration_server.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class RegistrationHandler implements ActionHandler {
    private final UserRepo userRepo;
    private final ObjectMapper mapper;

    @Override
    public Mono<Boolean> doAction(Action action) {
        User user = mapper.convertValue(action.getValue(), User.class);

        return userRepo.registration(user.getName(), user.getPassword(), user.getEmail(), user.getPhone(), "USER", user.getJobTitle())
                .map(user1 -> {
                    if (user1.getId() != null){
                        return true;
                    } else {
                        System.out.println(user1);
                        return false;
                    }
                });
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.REGISTRATION;
    }
}
