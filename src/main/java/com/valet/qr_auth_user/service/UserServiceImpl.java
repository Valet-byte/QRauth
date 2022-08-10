package com.valet.qr_auth_user.service;

import com.valet.qr_auth_user.model.User;
import com.valet.qr_auth_user.repo.UserRepo;
import com.valet.qr_auth_user.service.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo repo;
    private final PasswordEncoder encoder;
    @Override
    public Mono<User> registration(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user).map(a -> {a.setPassword("NONE"); return a;});
    }

    @Override
    public Mono<Void> delete(User admin) {
        return null;
    }
}
