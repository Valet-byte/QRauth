package com.valet.qremailserver.service.EmailCreator;

import com.valet.qremailserver.model.email.ActionType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateOrganizationEmailCreator implements EmailCreator {
    @Override
    public String createEmail(List<String> username, String link) {
        return "Здравствуйте, " + username.get(0) +". Перейдите по ссылке для завершения создания организации: " + link;
    }

    @Override
    public ActionType getMyType() {
        return ActionType.CREATE_ORGANIZATION;
    }
}
