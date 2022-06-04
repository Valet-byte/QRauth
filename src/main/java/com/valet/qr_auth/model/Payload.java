package com.valet.qr_auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payload{
    private Long creatorId;
    private Long pointId;
    private Long create_time;
    private String exp;
}
