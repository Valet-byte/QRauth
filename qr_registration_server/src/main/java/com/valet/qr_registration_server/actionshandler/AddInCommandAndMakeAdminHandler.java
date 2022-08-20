package com.valet.qr_registration_server.actionshandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.qr_registration_server.model.AdminToUser;
import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.model.changeAction.ActionType;
import com.valet.qr_registration_server.repo.AdminToUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class AddInCommandAndMakeAdminHandler implements ActionHandler {

    private final ObjectMapper mapper;
    private final AdminToUserRepo adminToUserRepo;
    private final ChangeRoleUserHandler changeRoleUserHandler;

    @Override
    @Transactional
    public Mono<Boolean> doAction(Action action) {
        AdminToUser adminToUser = mapper.convertValue(action.getValue(), AdminToUser.class);
        return adminToUserRepo.save(adminToUser)
                .publishOn(Schedulers.boundedElastic())
                .map(
                        atu -> {
                            if (atu != null){
                                Action action1 = new Action(null, atu.getUser_id(), "2", ActionType.CHANGE_ROLE);
                                changeRoleUserHandler.doAction(action1).subscribe();
                                return true;
                            }
                            return false;
                        }
                );
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.ADD_IN_COMMAND_AND_MAKE_ADMIN;
    }
}
