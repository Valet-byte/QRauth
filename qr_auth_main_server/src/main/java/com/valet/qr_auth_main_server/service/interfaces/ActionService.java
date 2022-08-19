package com.valet.qr_auth_main_server.service.interfaces;

import com.valet.qr_auth_main_server.model.changeAction.Action;
import com.valet.qr_auth_main_server.model.changeAction.ActionType;
import reactor.core.publisher.Mono;

public interface ActionService {
    boolean createAndSendAction(Long userId, Object value, ActionType actionType, String email);
    Action createAction(Long userId, Object value, ActionType actionType);
    Action createActionNotRegistrationInRedis(Long userId, Object value, ActionType actionType);
    Mono<Boolean> doAction(Action action);
    Mono<Boolean> doAction(String actionId);
}
