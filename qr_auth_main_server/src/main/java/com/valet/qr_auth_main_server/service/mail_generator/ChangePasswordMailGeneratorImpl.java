package com.valet.qr_auth_main_server.service.mail_generator;

import com.valet.qr_auth_main_server.model.changeAction.ActionType;
import com.valet.qr_auth_main_server.service.interfaces.MailGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordMailGeneratorImpl implements MailGenerator {
    @Value("${host}")
    private String host;

    @Override
    public String getMail(String data) {
        return "http://" + host + "/do/" + data;
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.CHANGE_PASSWORD;
    }
}
