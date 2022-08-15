package com.valet.qr_registration_server.serice;

import com.valet.qr_registration_server.model.Organization;
import com.valet.qr_registration_server.model.Role;
import com.valet.qr_registration_server.model.User;
import com.valet.qr_registration_server.repo.OrganizationRepo;
import com.valet.qr_registration_server.repo.RoleRepo;
import com.valet.qr_registration_server.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final OrganizationRepo organizationRepo;
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;


    @SneakyThrows
    @Override
    @Transactional
    public Mono<User> registration(User user) {

        return getOrganization(user).map(organization -> {
            user.setOrganization(organization);
            return user;
            }).log().then(getRole(user))
                .map(role -> {
                    user.setRole(role);
                    return user;
                }).log().doOnNext(this::getUser);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return userRepo.deleteById(id);
    }

    private Mono<User> getUser(User user){
        System.out.println("---------------------------------------------");
        System.out.println(user);
        return userRepo.registration(user.getName(), user.getPassword(), user.getEmail(), user.getPhone(),
                user.getOrganization().getId(), user.getRole().getId(), user.getJobTitle());
    }


    private Mono<Organization> getOrganization (User user){
        System.out.println("---------------------------------------------");
        System.out.println(user);
        return organizationRepo.findByName(user.getOrganization().getName());
    }

    private Mono<Role> getRole(User user){
        System.out.println("---------------------------------------------");
        System.out.println(user);
        return roleRepo.findByName(user.getRole().getName());
    }
}
