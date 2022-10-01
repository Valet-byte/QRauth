package com.valet.qrmainserver.service;

import com.valet.qrmainserver.model.Payload;
import com.valet.qrmainserver.model.Record;
import com.valet.qrmainserver.model.User;
import com.valet.qrmainserver.model.action.ActionType;
import com.valet.qrmainserver.repo.RecordRepo;
import com.valet.qrmainserver.service.interfaces.ActionService;
import com.valet.qrmainserver.service.interfaces.RecordService;
import com.valet.qrmainserver.service.interfaces.TeamService;
import com.valet.qrmainserver.service.interfaces.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepo repo;
    private final ActionService actionService;
    private final TokenService tokenService;
    private final TeamService teamService;

    @Override
    public boolean addRecord(double x, double y, String token, User user, LocalDateTime now) {
        Payload payload = tokenService.getPayload(token);
        if (payload.getTeamId().equals(teamService.getTeamIdByUserId(user.getId()))){
            return actionService.doAction(actionService.createAction(user.getId(), new Record(null, user.getId(), payload.getAdminId(), now), ActionType.ADD_RECORD));
        }
        return false;
    }

    @Override
    public List<Record> getAllRecord(String teamLeadId) {
        return repo.findAllByTeamLeadId(teamLeadId);
    }

    @Override
    public Page<Record> getRecord(String teamLeadId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repo.findAllByTeamLeadId(teamLeadId, pageable);
    }

    @Override
    public int count (String teamLeadId){
        return -1;
    }
}
