package com.valet.qrmainserver.repo;

import com.valet.qrmainserver.model.Record;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecordRepo extends MongoRepository<Record, String> {
    @NonNull Page<Record> findAllByTeamLeadId (String teamLeadId, @NonNull Pageable pageable);
    List<Record> findAllByTeamLeadId (String teamLeadId);
}
