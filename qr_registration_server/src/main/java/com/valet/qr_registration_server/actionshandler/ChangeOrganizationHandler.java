package com.valet.qr_registration_server.actionshandler;


import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.model.changeAction.ActionType;
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

    @Override
    public Mono<Boolean> doAction(Action action) {
        return organizationRepo.existsById((Long) action.getValue())
                .publishOn(Schedulers.boundedElastic())
                .map(exist -> {
                    if (exist){
                        userRepo.changeOrganizationUser(action.getUserId(), (Long) action.getValue()).subscribe(System.out::println);
                        return true;
                    }
                    return false;
                });
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.CHANGE_ORGANIZATION;
    }
}
