package com.valet.qremailserver.service.EmailCreator;

import com.valet.qremailserver.model.email.ActionType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloEmailCreatorImpl implements EmailCreator {
    @Override
    public String createEmail(List<String> username, String link) {
        return "Hello, " + username.get(0) + ". Welcome to Avocado";
    }

    @Override
    public ActionType getMyType() {
        return ActionType.HELLO_EMAIL;
    }
}
