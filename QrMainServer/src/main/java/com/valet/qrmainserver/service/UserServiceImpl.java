package com.valet.qrmainserver.service;

import com.valet.qrmainserver.model.dto.UserDTO;
import com.valet.qrmainserver.repo.UserRepo;
import com.valet.qrmainserver.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo repo;

    @Override
    @SneakyThrows
    public UserDTO getDtoById(String userId) {
        return repo.getById(userId);
    }

    @Override
    public long countByEmail(String email) {
        return repo.countByEmail(email);
    }
}
