package com.valet.qrmainserver.service.interfaces;

import com.valet.qrmainserver.model.Record;
import com.valet.qrmainserver.model.User;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface RecordService {

    boolean addRecord(double x, double y, String token, User user, LocalDateTime now);

    List<Record> getAllRecord(String teamLeadId);

    Page<Record> getRecord(String teamLeadId, int page, int size);
    int count (String teamLeadId);
}
