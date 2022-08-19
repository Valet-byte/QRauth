package com.valet.qr_auth_main_server.service;

import com.valet.qr_auth_main_server.model.changeAction.Action;
import com.valet.qr_auth_main_server.model.changeAction.ActionType;
import com.valet.qr_auth_main_server.repo.ActionRedisRepo;
import com.valet.qr_auth_main_server.service.interfaces.ActionService;
import com.valet.qr_auth_main_server.service.interfaces.DbUpdateService;
import com.valet.qr_auth_main_server.service.interfaces.EmailService;
import com.valet.qr_auth_main_server.service.interfaces.MailGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActionServiceImpl implements ActionService {

    private final EmailService emailService;

    private final Map<ActionType, MailGenerator> mailGeneratorMap;

    private final ActionRedisRepo actionRedisRepo;

    private final DbUpdateService dbUpdateService;

    @Autowired
    public ActionServiceImpl(ActionRedisRepo actionRedisRepo, EmailService emailService, List<MailGenerator> mailGenerators, DbUpdateService dbUpdateService) {
        this.emailService = emailService;
        mailGeneratorMap = mailGenerators.stream().collect(Collectors.toMap(MailGenerator::getMyActionType,
                                                                            mailGenerator -> mailGenerator));

        this.actionRedisRepo = actionRedisRepo;
        this.dbUpdateService = dbUpdateService;
    }


    @Override
    public boolean createAndSendAction(Long userId, Object value, ActionType actionType, String email) {

        Action action = createAction(userId, value, actionType);
        emailService.sendSimpleEmail(email, actionType.name(), mailGeneratorMap.get(actionType).getMail(action.getId()));

        return true;
    }

    @Override
    public Action createAction(Long userId, Object value, ActionType actionType) {

        Action action = new Action(null, userId, value, actionType);

        return registrationAction(action);
    }

    @Override
    public Action createActionNotRegistrationInRedis(Long userId, Object value, ActionType actionType) {
        return new Action(null, userId, value, actionType);
    }

    @Override
    public Mono<Boolean> doAction(Action action) {

        return dbUpdateService.doAction(action);
    }

    @Override
    public Mono<Boolean> doAction(String actionId) {
        return doAction(actionRedisRepo.findById(actionId).get());
    }

    private Action registrationAction(Action action){
        return actionRedisRepo.save(action);
    }
}
