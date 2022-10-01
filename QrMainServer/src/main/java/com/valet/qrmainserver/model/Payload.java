package com.valet.qrmainserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payload {
    private String organizationId;
    private double x;
    private double y;
    private int radius;
    private String adminId;
    private String exp;
    private String teamId;
}
