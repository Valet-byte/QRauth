package com.valet.qr_registration_server.actionshandler;


import com.valet.qr_registration_server.model.AdminToUser;
import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.model.changeAction.ActionType;
import com.valet.qr_registration_server.repo.AdminToUserRepo;
import com.valet.qr_registration_server.repo.OrganizationRepo;
import com.valet.qr_registration_server.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ChangeOrganizationHandler implements ActionHandler {

    private final OrganizationRepo organizationRepo;
    private final UserRepo userRepo;
    private final AdminToUserRepo adminToUser;

    @Override
    public Mono<Boolean> doAction(Action action) {

        return organizationRepo.findIdByName((String) action.getValue())
                .publishOn(Schedulers.boundedElastic())
                .map(id -> {
                    userRepo.changeOrganizationUser(action.getUserId(), id).subscribe(System.out::println);
                    adminToUser.deleteAdminToUserByAdminIdOrUserId(action.getUserId()).subscribe();
                    return true;
                });
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.CHANGE_ORGANIZATION;
    }
}
