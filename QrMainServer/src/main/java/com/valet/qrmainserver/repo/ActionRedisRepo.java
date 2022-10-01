package com.valet.qrmainserver.repo;

import com.valet.qrmainserver.model.action.Action;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface ActionRedisRepo extends KeyValueRepository<Action, String> {
}
