package com.valet.qr_auth_admin.repo;

import com.valet.qr_auth_admin.model.changeAction.Action;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface ActionRedisRepo extends KeyValueRepository<Action, String> {
}
