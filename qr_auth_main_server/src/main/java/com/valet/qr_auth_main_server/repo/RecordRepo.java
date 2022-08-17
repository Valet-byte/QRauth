package com.valet.qr_auth_main_server.repo;

import com.valet.qr_auth_main_server.model.Record;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RecordRepo extends ReactiveCrudRepository<Record, Long> {
}
