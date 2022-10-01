package com.valet.qrmainserver.model.action;

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
    private String userId;
    private Object value;
    private ActionType actionType;

}
