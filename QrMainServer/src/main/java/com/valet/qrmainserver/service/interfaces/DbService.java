package com.valet.qrmainserver.service.interfaces;

import com.valet.qrmainserver.model.action.Action;

public interface DbService {
    boolean doAction(Action action);
}
