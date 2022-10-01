package com.valet.qrdbserver.service;

import com.valet.qrdbserver.handler.ActionHandler;
import com.valet.qrdbserver.model.action.Action;
import com.valet.qrdbserver.model.action.ActionType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActionServiceImpl implements ActionService {

    private final Map<ActionType, ActionHandler> handlerMap;

    public ActionServiceImpl(List<ActionHandler> handlers) {
        handlerMap = handlers.stream().collect(Collectors.toMap(ActionHandler::getMyActionType, actionHandler -> actionHandler));
    }

    @Override
    public boolean doAction(Action action) {
        return handlerMap.get(action.getActionType()).doAction(action);
    }
}
