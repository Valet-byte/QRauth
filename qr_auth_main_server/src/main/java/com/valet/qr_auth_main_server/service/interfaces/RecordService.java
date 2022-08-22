package com.valet.qr_auth_main_server.service.interfaces;

import com.valet.qr_auth_main_server.model.Record;
import com.valet.qr_auth_main_server.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface RecordService {
    Mono<Boolean> addRecord(double x, double y, String token, User user, LocalDateTime now);

    Flux<Record> getAllRecord(Long adminId, LocalDate from, LocalDate to);
}
