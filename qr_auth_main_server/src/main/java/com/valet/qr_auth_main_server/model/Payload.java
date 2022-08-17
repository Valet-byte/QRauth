package com.valet.qr_auth_main_server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payload {
    private String organization;
    private double x;
    private double y;
    private int radius;
    private Long adminId;
    private String exp;
}
