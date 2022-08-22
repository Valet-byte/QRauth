package com.valet.qr_registration_server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Record {
    @Id
    private Long id;
    @Column("user_id") private Long userId;
    @Column("admin_id") private Long adminId;
    @Column("date_time") private LocalDateTime dateTime;
}