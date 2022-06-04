package com.valet.qr_auth.service.interfaces;

import com.valet.qr_auth.model.Person;

import java.util.List;

public interface RecordService {
    List<Person> getAllRecordsByPointId(Long pointId);
    Long addNewRecord(Long userId, String token, String typeRecord, Double x, Double y);
}
