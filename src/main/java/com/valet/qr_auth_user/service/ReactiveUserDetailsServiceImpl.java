package com.valet.qr_auth_user.service;

import com.valet.qr_auth_user.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor

public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final UserRepo repo;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        try {
            return repo.findByEmail(username).cast(UserDetails.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}

