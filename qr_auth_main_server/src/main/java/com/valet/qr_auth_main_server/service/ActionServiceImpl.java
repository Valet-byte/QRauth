package com.valet.qr_auth_main_server.service;

import com.valet.qr_auth_main_server.handlers.actionshandler.ActionHandler;
import com.valet.qr_auth_main_server.model.User;
import com.valet.qr_auth_main_server.model.changeAction.Action;
import com.valet.qr_auth_main_server.model.changeAction.ActionType;
import com.valet.qr_auth_main_server.repo.ActionRedisRepo;
import com.valet.qr_auth_main_server.service.interfaces.ActionService;
import com.valet.qr_auth_main_server.service.interfaces.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActionServiceImpl implements ActionService {

    private final ActionRedisRepo actionRedisRepo;
    private final PasswordEncoder encoder;
    private final EmailService emailService;
    private final Map<ActionType, ActionHandler> handlerMap;
    @Value("${host}")
    private String host;

    @Autowired
    public ActionServiceImpl(ActionRedisRepo actionRedisRepo, PasswordEncoder encoder, EmailService emailService
    , List<ActionHandler> actionHandlers) {
        this.actionRedisRepo = actionRedisRepo;
        this.encoder = encoder;
        this.emailService = emailService;

        handlerMap = actionHandlers.stream().collect(Collectors.toMap(ActionHandler::getMyActionType, actionHandler -> actionHandler));
    }

    @Override
    public boolean changePassword(Long userId, String userEmail, String password) {
        return false;
    }

    @Override
    public boolean changeJobTitle(Long userId, String userEmail, String jobTitle) {
        return false;
    }

    @Override
    public boolean changeName(Long userId, String userEmail, String name) {
        return false;
    }

    @Override
    public boolean registration(User user) {

        user.setPassword(encoder.encode(user.getPassword()));
        Action action = new Action(null, null, user, ActionType.REGISTRATION);
        String id = actionRedisRepo.save(action).getId();

        emailService.sendSimpleEmail(user.getEmail(),
                "Registration", "http://" + host + ":9630/do/" +  id);

        return true;
    }

    @Override
    public boolean doAction(String actionId) {

        Action action = actionRedisRepo.findById(actionId).get();

        return handlerMap.get(action.getActionType()).doAction(action);
    }
}
