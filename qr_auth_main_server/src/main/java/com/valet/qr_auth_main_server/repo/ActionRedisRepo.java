package com.valet.qr_auth_main_server.repo;

import com.valet.qr_auth_main_server.model.changeAction.Action;
import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface ActionRedisRepo extends KeyValueRepository<Action, String> {
}
