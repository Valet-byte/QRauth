package com.valet.qr_auth_user.service.interfaces;

import com.valet.qr_auth_user.model.Record;
import com.valet.qr_auth_user.model.User;
import reactor.core.publisher.Mono;

public interface RecordService {
    Mono<Record> addRecord(double x, double y, String token, User user);
}
