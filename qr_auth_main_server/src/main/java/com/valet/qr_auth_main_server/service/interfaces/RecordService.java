package com.valet.qr_auth_main_server.service.interfaces;

import com.valet.qr_auth_main_server.model.Record;
import com.valet.qr_auth_main_server.model.User;
import reactor.core.publisher.Mono;

public interface RecordService {
    Mono<Record> addRecord(double x, double y, String token, User user);
}
