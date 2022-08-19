package com.valet.qr_registration_server.service;

import com.valet.qr_registration_server.actionshandler.ActionHandler;
import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.model.changeAction.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActionServiceImpl implements ActionService {

    private final Map<ActionType, ActionHandler> handlerMap;

    @Autowired
    public ActionServiceImpl(List<ActionHandler> handlers){
        handlerMap = handlers.stream().collect(Collectors.toMap(ActionHandler::getMyActionType,
                                                                handler -> handler));
    }

    @Override
    public Mono<Boolean> doAction(Action action) {
        return handlerMap.get(action.getActionType()).doAction(action);
    }
}
