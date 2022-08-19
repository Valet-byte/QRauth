package com.valet.qr_registration_server.actionshandler;

import com.valet.qr_registration_server.model.Organization;
import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.model.changeAction.ActionType;
import com.valet.qr_registration_server.repo.OrganizationRepo;
import com.valet.qr_registration_server.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@AllArgsConstructor
public class CreateOrganizationHandler implements ActionHandler {

    private final OrganizationRepo organizationRepo;
    private final UserRepo userRepo;

    @Override
    public Mono<Boolean> doAction(Action action) {
        Organization organization = new Organization(null, (String) action.getValue(), action.getUserId());
        return organizationRepo.save(organization)
                .publishOn(Schedulers.boundedElastic())
                .map(organization1 -> {
                    if (organization1.getId() != null){
                        userRepo.changeOrganizationUser(action.getUserId(), organization1.getId()).subscribe();
                        userRepo.changeRoleUser(action.getUserId(), 4L).subscribe();
                        return true;
                    } else {
                        return false;
                    }
                });
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.CREATE_ORGANIZATION;
    }
}
