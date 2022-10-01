package com.valet.qrdbserver.handler;

import com.valet.qrdbserver.model.action.Action;
import com.valet.qrdbserver.model.action.ActionType;

public interface ActionHandler {
    boolean doAction(Action action);

    ActionType getMyActionType();

}
