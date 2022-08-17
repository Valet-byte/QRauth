package com.valet.qr_registration_server.serice;

import com.valet.qr_registration_server.model.User;
import com.valet.qr_registration_server.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;

    @Override
    public Mono<User> registration(User user) {
        return getUser(user);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return userRepo.deleteById(id);
    }

    private Mono<User> getUser(User user){
        return userRepo.registration(user.getName(), user.getPassword(), user.getEmail(), user.getPhone(),
                user.getOrganization().getName(), user.getRole().getName(), user.getJobTitle());
    }
}
