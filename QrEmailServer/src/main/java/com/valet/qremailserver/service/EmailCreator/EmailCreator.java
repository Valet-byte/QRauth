package com.valet.qremailserver.service.EmailCreator;

import com.valet.qremailserver.model.email.ActionType;

import java.util.List;

public interface EmailCreator {
    String createEmail(List<String> username, String link);
    ActionType getMyType();
}
