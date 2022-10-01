package com.valet.qrdbserver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.qrdbserver.model.Record;
import com.valet.qrdbserver.model.action.Action;
import com.valet.qrdbserver.model.action.ActionType;
import com.valet.qrdbserver.repo.RecordRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecordActionHandler implements ActionHandler {

    private final ObjectMapper mapper;
    private final RecordRepo repo;

    @Override
    public boolean doAction(Action action) {
        try {
            Record record = mapper.convertValue(action.getValue(), Record.class);
            repo.save(record);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ActionType getMyActionType() {
        return ActionType.ADD_RECORD;
    }
}
