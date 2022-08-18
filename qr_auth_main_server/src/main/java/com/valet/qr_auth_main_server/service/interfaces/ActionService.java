package com.valet.qr_auth_main_server.service.interfaces;

import com.valet.qr_auth_main_server.model.User;
import reactor.core.publisher.Mono;

public interface ActionService {
    boolean changePassword(Long userId, String userEmail, String password);
    boolean changeJobTitle(Long userId, String userEmail, String jobTitle);
    boolean changeName(Long userId, String userEmail, String name);
    Mono<Boolean> registration(User user);
    boolean doAction(String actionId);
    boolean changeRoleUser(long userId, long roleId);

    void fastOrganizationChange(Long creatorId, Long id);

    Boolean changeOrganization(Long id, String email, String organizationName);
}
