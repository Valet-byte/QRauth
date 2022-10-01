package com.valet.qrdbserver.model.action;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Action {
    @Id
    private String id;
    private String userId;
    private Object value;
    private ActionType actionType;

}
