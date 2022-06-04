package com.valet.qr_auth.repo;

import com.valet.qr_auth.model.Person;

import java.util.List;

public interface RecordRepo {
    List<Person> getAllRecordsByPointId(Long pointId);
    Long addNewRecord(Long userId, Long pointId, String typeRecord);
}
