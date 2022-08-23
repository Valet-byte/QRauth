package com.valet.qr_auth_main_server.service;

import com.valet.qr_auth_main_server.repo.OrganizationRepo;
import com.valet.qr_auth_main_server.repo.RoleRepo;
import com.valet.qr_auth_main_server.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final UserRepo repo;
    private final RoleRepo roleRepo;
    private final OrganizationRepo organizationRepo;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        try {
            return repo.findByEmail(username).flatMap(user -> roleRepo.findById(user.getRole().getId())
                    .map(role -> {
                        user.setRole(role);
                        return user;
                    })).flatMap(user -> {
                               if (user.getOrganization() == null){
                                   return Mono.just(user);
                               } else {
                                   return organizationRepo.findById(user.getOrganization().getId()).map(org -> {
                                       user.setOrganization(org);
                                       return user;
                                   });
                               }
                    })


                    .cast(UserDetails.class);

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
