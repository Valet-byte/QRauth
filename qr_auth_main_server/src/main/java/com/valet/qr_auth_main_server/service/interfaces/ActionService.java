package com.valet.qr_auth_main_server.service.interfaces;

import com.valet.qr_auth_main_server.model.User;

public interface ActionService {
    boolean changePassword(Long userId, String userEmail, String password);
    boolean changeJobTitle(Long userId, String userEmail, String jobTitle);
    boolean changeName(Long userId, String userEmail, String name);

    boolean registration(User user);

    boolean doAction(String actionId);
}
