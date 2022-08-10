package com.valet.qr_auth_user.repo;

import com.valet.qr_auth_user.model.Record;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RecordRepo extends ReactiveCrudRepository<Record, Long> {
}

