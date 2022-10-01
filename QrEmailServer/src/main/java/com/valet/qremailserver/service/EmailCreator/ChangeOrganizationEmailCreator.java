package com.valet.qremailserver.service.EmailCreator;

import com.valet.qremailserver.model.email.ActionType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ChangeOrganizationEmailCreator implements EmailCreator {
    @Override
    public String createEmail(List<String> username, String link) {
        return "Здравствуйте, " + username.get(0) + ". Пользователь " + username.get(1) + "хочет добавть " +
                "вас в свою организацию. Для одобрения перейдите по ссылке: " + link;
    }

    @Override
    public ActionType getMyType() {
        return ActionType.CHANGE_ORGANIZATION;
    }
}
