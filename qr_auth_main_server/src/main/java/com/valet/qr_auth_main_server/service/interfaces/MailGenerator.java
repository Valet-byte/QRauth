package com.valet.qr_auth_main_server.service.interfaces;

import com.valet.qr_auth_main_server.model.changeAction.ActionType;

public interface MailGenerator {

    String getMail(String data);
    ActionType getMyActionType();

}
