package com.valet.qr_auth_main_server.repo;

import com.valet.qr_auth_main_server.model.Record;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;

public interface RecordRepo extends ReactiveCrudRepository<Record, Long> {
    @Query("SELECT * FROM Record r WHERE r.admin_id = :adminId AND r.date_time::date >= :from AND r.date_time::date <= :to")
    Flux<Record> findAllRecordWhoMadeFromAndTo(Long adminId, LocalDate from, LocalDate to);
}
