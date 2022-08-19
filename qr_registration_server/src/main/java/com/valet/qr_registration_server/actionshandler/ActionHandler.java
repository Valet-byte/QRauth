package com.valet.qr_registration_server.actionshandler;


import com.valet.qr_registration_server.model.changeAction.Action;
import com.valet.qr_registration_server.model.changeAction.ActionType;
import reactor.core.publisher.Mono;

public interface ActionHandler {

    Mono<Boolean> doAction(Action action);

    ActionType getMyActionType();

}
