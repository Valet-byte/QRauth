package com.valet.qr_auth_main_server.handlers.actionshandler;

import com.valet.qr_auth_main_server.model.changeAction.Action;
import com.valet.qr_auth_main_server.model.changeAction.ActionType;

public interface ActionHandler {

    boolean doAction(Action action);

    ActionType getMyActionType();

}
