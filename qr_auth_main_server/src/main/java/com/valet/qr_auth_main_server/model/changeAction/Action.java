package com.valet.qr_auth_main_server.model.changeAction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash(value = "action", timeToLive = 300)
public class Action {
    @Id
    private String id;
    private Long userId;
    private Object value;
    private ActionType actionType;
}
