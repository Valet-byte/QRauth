package com.valet.qr_auth_main_server.handlers.actionshandler;

import com.valet.qr_auth_main_server.model.User;
import com.valet.qr_auth_main_server.model.changeAction.Action;
import com.valet.qr_auth_main_server.model.changeAction.ActionType;
import com.valet.qr_auth_main_server.service.interfaces.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationHandler implements ActionHandler {

    private final RegistrationService registrationService;

    @Override
    public boolean doAction(Action action) {
        registrationService.registration((User) action.getValue()).doOnError(Throwable::printStackTrace);
        return false;
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.REGISTRATION;
    }
}
