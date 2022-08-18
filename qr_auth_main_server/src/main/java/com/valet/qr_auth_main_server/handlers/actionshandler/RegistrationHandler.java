package com.valet.qr_auth_main_server.handlers.actionshandler;

import com.valet.qr_auth_main_server.model.Organization;
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
        ((User) action.getValue()).setOrganization(new Organization());
        registrationService.registration((User) action.getValue()).subscribe(System.out::println);
        return true;
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.REGISTRATION;
    }
}
