package com.valet.qrmainserver.service;

import com.valet.qrmainserver.model.Team;
import com.valet.qrmainserver.model.TeamToUser;
import com.valet.qrmainserver.service.interfaces.TeamService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@AllArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final MongoTemplate template;

    @Override
    @SneakyThrows
    public Team getByLeadId(String leadId) {
        Query query = new Query(Criteria.where("adminId").is(leadId));
        return template.findOne(query, Team.class);
    }

    @Override
    public String getIdByLeadId(String leadId) {
        Query query = new Query(Criteria.where("adminId").is(leadId));
        query.fields().exclude("name", "organizationId", "adminId");
        var team = Objects.requireNonNull(template.findOne(query, Team.class));
        return team.getId();
    }

    @Override
    public boolean existUserInAnyTeam(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        return template.exists(query, TeamToUser.class);
    }

    @Override
    public String getTeamIdByUserId(String userId) {
        Query query = new Query(Criteria.where("userId").is(userId));
        query.fields().exclude("userId", "_id");
        var team = Objects.requireNonNull(template.findOne(query, TeamToUser.class));
        return team.getTeamId();
    }
}
