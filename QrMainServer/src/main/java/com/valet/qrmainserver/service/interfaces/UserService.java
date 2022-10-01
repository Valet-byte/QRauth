package com.valet.qrmainserver.service.interfaces;

import com.valet.qrmainserver.model.dto.UserDTO;

public interface UserService {
    UserDTO getDtoById(String userId);

    long countByEmail (String email);
}
