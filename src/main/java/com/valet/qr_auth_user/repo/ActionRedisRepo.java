package com.valet.qr_auth_user.repo;

import com.valet.qr_auth_user.model.changeAction.Action;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface ActionRedisRepo extends KeyValueRepository<Action, String> {
}

