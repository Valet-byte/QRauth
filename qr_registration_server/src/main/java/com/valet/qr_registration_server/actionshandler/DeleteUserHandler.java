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
import reactor.core.scheduler.Schedulers;

@Service
@AllArgsConstructor
public class DeleteUserHandler implements ActionHandler {

    private final UserRepo userRepo;
    private final OrganizationRepo organizationRepo;
    private final AdminToUserRepo adminToUserRepo;
    private final ObjectMapper mapper;

    @Override
    public Mono<Boolean> doAction(Action action) {
        User user = mapper.convertValue(action.getValue(), User.class);
        System.out.println(user);

        if (user.getRole().getId() == 4L){
            organizationRepo.delete(user.getOrganization()).subscribe();
            adminToUserRepo.deleteAllByIds(userRepo.findIdByOrganizationId(user.getOrganization().getId())).subscribe();
            userRepo.setNullOrgIdAllUsersByIds(user.getOrganization().getId()).subscribe();
            return userRepo.delete(user)
                    .map(v -> true);

        } else {
            adminToUserRepo.deleteAdminToUserByAdminIdOrUserId(user.getId()).subscribe();
            return userRepo.deleteById(user.getId())
                    .log()
                    .publishOn(Schedulers.boundedElastic())
                    .map(v -> true);
        }
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.DELETE_USER;
    }
}
