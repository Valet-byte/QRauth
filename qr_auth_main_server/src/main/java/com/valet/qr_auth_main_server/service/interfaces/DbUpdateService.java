package com.valet.qr_auth_main_server.service.interfaces;

import com.valet.qr_auth_main_server.model.changeAction.Action;
import reactor.core.publisher.Mono;

public interface DbUpdateService {

    Mono<Boolean> doAction(Action action);
}
