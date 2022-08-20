package com.valet.qr_auth_main_server.service.interfaces;

import com.valet.qr_auth_main_server.model.User;
import com.valet.qr_auth_main_server.model.dto.UserDTO;
import reactor.core.publisher.Flux;

public interface CommandService {
    Flux<UserDTO> getMyCommand(Long userId);
}
