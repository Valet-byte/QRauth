package com.valet.qr_registration_server.actionshandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.qr_registration_server.model.AdminToUser;
import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.model.changeAction.ActionType;
import com.valet.qr_registration_server.repo.AdminToUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@AllArgsConstructor
public class AddInCommandHandler implements ActionHandler {

    private final AdminToUserRepo adminToUserRepo;
    private final ObjectMapper mapper;

    @Override
    public Mono<Boolean> doAction(Action action) {
        AdminToUser adminToUser = mapper.convertValue(action.getValue(), AdminToUser.class);
        return adminToUserRepo.save(adminToUser)
                .map(Objects::nonNull);
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.ADD_IN_COMMAND;
    }
}
