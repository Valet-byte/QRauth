package com.valet.qrdbserver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CreateOrganizationActionHandler implements ActionHandler {

    private final ObjectMapper mapper;
    private final MongoTemplate template;

    @Override
    public boolean doAction(Action action) {
        try {
            Organization organization = mapper.convertValue(action.getValue(), Organization.class);
            organization.setCreatorId(action.getUserId());
            organization.setPayToken("first_month");

            organization = template.save(organization);

            Query query = new Query(Criteria.where("id").is(action.getUserId()));
            Update update = new Update();
            update.set("role", "ORGANIZATION_CREATOR");
            update.set("organizationId", organization.getId());

            template.updateFirst(query, update, User.class);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.CREATE_ORGANIZATION;
    }
}
