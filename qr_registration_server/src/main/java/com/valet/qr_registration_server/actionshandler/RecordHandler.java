package com.valet.qr_registration_server.actionshandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.qr_registration_server.model.Record;
import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.model.changeAction.ActionType;
import com.valet.qr_registration_server.repo.RecordRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@AllArgsConstructor
public class RecordHandler implements ActionHandler {

    private final ObjectMapper mapper;
    private final RecordRepo recordRepo;

    @Override
    public Mono<Boolean> doAction(Action action) {
        Record record = mapper.convertValue(action.getValue(), Record.class);
        return recordRepo.save(record).map(Objects::nonNull);
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.ADD_RECORD;
    }
}
