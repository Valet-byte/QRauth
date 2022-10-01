package com.valet.qrdbserver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.qrdbserver.model.TeamToUser;
import com.valet.qrdbserver.model.action.Action;
import com.valet.qrdbserver.model.action.ActionType;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import static com.valet.qrdbserver.model.action.ActionType.ADD_IN_TEAM;

@Component
@AllArgsConstructor
public class AddInTeamActionHandler implements ActionHandler {

    private final MongoTemplate template;
    private final ObjectMapper mapper;

    @Override
    public boolean doAction(Action action) {
        try {
            TeamToUser teamToUser = mapper.convertValue(action.getValue(), TeamToUser.class);
            template.save(teamToUser);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ActionType getMyActionType() {
        return ADD_IN_TEAM;
    }
}
