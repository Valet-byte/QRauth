package com.valet.qrmainserver.service.interfaces;

import com.valet.qrmainserver.model.Team;

public interface TeamService {
    Team getByLeadId(String leadId);
    String getIdByLeadId(String leadId);
    boolean existUserInAnyTeam(String userId);
    String getTeamIdByUserId(String userId);
}
