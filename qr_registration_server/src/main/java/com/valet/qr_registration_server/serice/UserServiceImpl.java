package com.valet.qr_registration_server.serice;

import com.valet.qr_registration_server.model.Role;
import com.valet.qr_registration_server.model.User;
import com.valet.qr_registration_server.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;

    @Override
    public Mono<User> registration(User user) {
        System.out.println(user);
        user.setRole(new Role(null, "USER"));
        return getUser(user);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return userRepo.deleteById(id);
    }

    @Override
    public Mono<Void> changeRoleUser(Long userId, Long roleId) {
        return userRepo.changeRoleUser(userId, roleId);
    }

    @Override
    public Mono<Void> changeOrganizationUser(Long userId, Long orgId) {
        return userRepo.changeOrganizationUser(userId, orgId);
    }

    private Mono<User> getUser(User user){
        return userRepo.registration(user.getName(), user.getPassword(), user.getEmail(), user.getPhone(),
                 user.getRole().getName(), user.getJobTitle());
    }
}
