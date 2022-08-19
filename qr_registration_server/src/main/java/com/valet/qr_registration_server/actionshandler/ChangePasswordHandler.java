package com.valet.qr_registration_server.actionshandler;

import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.model.changeAction.ActionType;
import com.valet.qr_registration_server.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ChangePasswordHandler implements ActionHandler {

    private final UserRepo userRepo;
    @Override
    public Mono<Boolean> doAction(Action action) {
        return userRepo.changePasswordById(action.getUserId(), (String) action.getValue())
                .map(i -> i != null && i > 0);
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.CHANGE_PASSWORD;
    }
}
