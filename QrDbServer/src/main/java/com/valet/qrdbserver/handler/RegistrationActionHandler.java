package com.valet.qrdbserver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.qrdbserver.model.Email;
import com.valet.qrdbserver.model.User;
import com.valet.qrdbserver.model.action.Action;
import com.valet.qrdbserver.model.action.ActionType;
import com.valet.qrdbserver.repo.UserRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegistrationActionHandler implements ActionHandler {

    private final UserRepo userRepo;
    private final ObjectMapper mapper;
    private final KafkaTemplate<String, Email> template;

    @Value("${defaultRole}")
    private String defaultRole;

    @Override
    public boolean doAction(Action action) {

        try {
            User user = mapper.convertValue(action.getValue(), User.class);
            user.setRole(defaultRole);
            userRepo.save(user);
            template.send("sendEmailTopic", new Email(user.getEmail(), "Welocme to Avocado", List.of(user.getName()), null, ActionType.HELLO_EMAIL));
            return true;
        } catch (Exception e){
            e.printStackTrace();

        }

        return false;
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.REGISTRATION;
    }
}
