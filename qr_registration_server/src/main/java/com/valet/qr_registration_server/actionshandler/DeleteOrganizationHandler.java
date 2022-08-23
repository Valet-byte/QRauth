package com.valet.qr_registration_server.actionshandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.qr_registration_server.model.User;
import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.model.changeAction.ActionType;
import com.valet.qr_registration_server.repo.AdminToUserRepo;
import com.valet.qr_registration_server.repo.OrganizationRepo;
import com.valet.qr_registration_server.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteOrganizationHandler implements ActionHandler {

    private final OrganizationRepo organizationRepo;
    private final UserRepo userRepo;
    private final AdminToUserRepo adminToUserRepo;
    private final ObjectMapper mapper;
    @Override
    public Mono<Boolean> doAction(Action action) {
        User user = mapper.convertValue(action.getValue(), User.class);
        userRepo.setNullOrgIdAllUsersByIds(user.getOrganization().getId()).subscribe();
        adminToUserRepo.deleteAllByIds(userRepo.findIdByOrganizationId(user.getOrganization().getId())).subscribe();
        organizationRepo.delete(user.getOrganization()).subscribe();

        return userRepo.changeRoleUser(user.getId(), 1L).map(i -> true);
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.DELETE_ORGANIZATION;
    }
}
