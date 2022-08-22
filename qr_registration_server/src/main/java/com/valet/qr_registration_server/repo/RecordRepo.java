package com.valet.qr_registration_server.repo;

import com.valet.qr_registration_server.model.Record;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RecordRepo extends ReactiveCrudRepository<Record, Long> {
}
