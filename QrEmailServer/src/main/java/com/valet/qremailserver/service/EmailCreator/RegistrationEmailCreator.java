package com.valet.qremailserver.service.EmailCreator;

import com.valet.qremailserver.model.email.ActionType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegistrationEmailCreator implements EmailCreator {
    @Override
    public String createEmail(List<String> username, String link) {
        return "Пожалуйста перейдите по ссылке для завершения регистрации: " + link;
    }

    @Override
    public ActionType getMyType() {
        return ActionType.REGISTRATION;
    }
}
