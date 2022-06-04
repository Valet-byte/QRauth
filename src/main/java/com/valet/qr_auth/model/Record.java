package com.valet.qr_auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Record {
    private Long id;
    private Long userId;
    private Long pointId;
    private LocalDateTime dateTime;
    private String typeRecord;
}
