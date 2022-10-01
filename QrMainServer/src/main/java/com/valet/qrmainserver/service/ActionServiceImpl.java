package com.valet.qrmainserver.service;

import com.valet.qrmainserver.model.action.Action;
import com.valet.qrmainserver.model.action.ActionType;
import com.valet.qrmainserver.model.email.Email;
import com.valet.qrmainserver.repo.ActionRedisRepo;
import com.valet.qrmainserver.service.interfaces.ActionService;
import com.valet.qrmainserver.service.interfaces.DbService;
import com.valet.qrmainserver.service.interfaces.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRedisRepo actionRedisRepo;
    private final EmailService emailService;
    private final DbService dbService;
    @Value("${host}")
    private String link;

    @Override
    public boolean createAndSendAction(String userId, Object value, ActionType actionType, String email, String... username) {

        Action action = actionRedisRepo.save(createAction(userId, value, actionType));

        emailService.sendEmail(userId, new Email(email, "Registration in Avocado!", Arrays.stream(username).toList(), link + action.getId(), actionType));
        return true;
    }

    @Override
    public Action createAction(String userId, Object value, ActionType actionType) {
        return new Action(null, userId, value, actionType);
    }

    @Override
    public boolean doAction(Action action) {
        return dbService.doAction(action);
    }

    @Override
    public boolean doAction(String actionId) {
        return doAction(actionRedisRepo.findById(actionId).get());
    }
}
