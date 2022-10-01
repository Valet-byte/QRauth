package com.valet.qrdbserver.handler;

import com.valet.qrdbserver.model.Organization;
import com.valet.qrdbserver.model.Team;
import com.valet.qrdbserver.model.TeamToUser;
import com.valet.qrdbserver.model.User;
import com.valet.qrdbserver.model.action.Action;
import com.valet.qrdbserver.model.action.ActionType;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteUserActionHandler implements ActionHandler {

    private final MongoTemplate template;

    @Override
    public boolean doAction(Action action) {

        try {
            Query query = new Query(Criteria.where("userId").orOperator(Criteria.where("adminId"), Criteria.where("creatorId")).is(action.getUserId()));

            template.remove(query, Organization.class);
            template.remove(query, Team.class);
            template.remove(query, TeamToUser.class);

            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.DELETE_USER;
    }
}
