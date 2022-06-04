package com.valet.qr_auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Point {
    private Long id;
    private Long creatorId;
    private Double x;
    private Double y;
    private Integer radius;
    private LocalDateTime createTime;
    private LocalDateTime actualTime;
}
