package com.valet.qrdbserver.repo;

import com.valet.qrdbserver.model.Record;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecordRepo extends MongoRepository<Record, String> {
}
