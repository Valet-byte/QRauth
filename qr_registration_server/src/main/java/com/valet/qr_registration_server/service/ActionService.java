package com.valet.qr_registration_server.service;

import com.valet.qr_registration_server.model.changeAction.Action;
import reactor.core.publisher.Mono;

public interface ActionService {
    Mono<Boolean> doAction(Action action);
}
