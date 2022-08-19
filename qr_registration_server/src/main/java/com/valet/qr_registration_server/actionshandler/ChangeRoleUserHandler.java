package com.valet.qr_registration_server.actionshandler;


import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.model.changeAction.ActionType;
import com.valet.qr_registration_server.repo.RoleRepo;
import com.valet.qr_registration_server.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@AllArgsConstructor
public class ChangeRoleUserHandler implements ActionHandler {

    private final RoleRepo roleRepo;
    private final UserRepo userRepo;

    @Override
    public Mono<Boolean> doAction(Action action) {
        return roleRepo.existsById(Long.valueOf(((String)action.getValue())))
                .publishOn(Schedulers.boundedElastic())
                .map(exist -> {
                    if (exist){
                        userRepo.changeRoleUser(action.getUserId(), Long.valueOf(((String)action.getValue()))).subscribe(System.out::println);
                        return true;
                    }
                    return false;
                });
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.CHANGE_ROLE;
    }
}
