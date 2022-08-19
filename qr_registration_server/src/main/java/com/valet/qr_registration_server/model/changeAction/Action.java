package com.valet.qr_registration_server.model.changeAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Action {
    private String id;
    private Long userId;
    private Object value;
    private ActionType actionType;
}
