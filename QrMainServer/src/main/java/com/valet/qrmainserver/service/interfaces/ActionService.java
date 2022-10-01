package com.valet.qrmainserver.service.interfaces;

import com.valet.qrmainserver.model.action.Action;
import com.valet.qrmainserver.model.action.ActionType;
import org.apache.kafka.common.protocol.types.Field;
import reactor.core.publisher.Mono;

public interface ActionService {
    boolean createAndSendAction(String userId, Object value, ActionType actionType, String email, String... username);
    Action createAction(String userId, Object value, ActionType actionType);
    boolean doAction(Action action);
    boolean doAction(String actionId);
}