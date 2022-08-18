package com.valet.qr_auth_main_server.handlers.actionshandler;

import com.valet.qr_auth_main_server.model.changeAction.Action;
import com.valet.qr_auth_main_server.model.changeAction.ActionType;
import com.valet.qr_auth_main_server.repo.OrganizationRepo;
import com.valet.qr_auth_main_server.service.interfaces.OrganizationService;
import com.valet.qr_auth_main_server.service.interfaces.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangeOrganizationHandler implements ActionHandler{

    private final RegistrationService registrationService;
    @Autowired
    private OrganizationRepo organizationService;

    @Override
    public boolean doAction(Action action) {
        if (action.getValue() instanceof Long){
            registrationService.changeOrganizationUser(action.getUserId(), (long) action.getValue()).subscribe(System.out::println);
        } else if (action.getValue() instanceof String){
            organizationService.findIdByName((String)action.getValue())
                    .flatMap(id -> registrationService.changeOrganizationUser(action.getUserId(), id)).subscribe(System.out::println);


        }

        return true;
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.CHANGE_ORGANIZATION;
    }
}
