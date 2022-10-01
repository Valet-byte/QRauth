package com.valet.qrdbserver.handler;

import com.valet.qrdbserver.model.Organization;
import com.valet.qrdbserver.model.User;
import com.valet.qrdbserver.model.action.Action;
import com.valet.qrdbserver.model.action.ActionType;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ChangeOwnOrganizationActionHandler implements ActionHandler {

    private final MongoTemplate template;

    @Override
    public boolean doAction(Action action) {
        try {
            Query query = new Query(Criteria.where("id").is(action.getUserId()));
            Update update = new Update();
            update.set("creatorId", action.getValue());
            template.updateFirst(query, update, Organization.class);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.CHANGE_OWN_ORGANIZATION;
    }
}
