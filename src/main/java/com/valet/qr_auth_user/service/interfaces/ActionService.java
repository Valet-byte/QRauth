package com.valet.qr_auth_user.service.interfaces;

public interface ActionService {
    boolean changePassword(Long userId, String userEmail, String password);
    boolean changeJobTitle(Long userId, String userEmail, String jobTitle);
    boolean changeName(Long userId, String userEmail, String name);

    boolean doAction(String actionId);
}
