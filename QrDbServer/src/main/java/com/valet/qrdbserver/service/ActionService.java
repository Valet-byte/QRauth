package com.valet.qrdbserver.service;

import com.valet.qrdbserver.model.action.Action;

public interface ActionService {
    boolean doAction(Action action);
}
