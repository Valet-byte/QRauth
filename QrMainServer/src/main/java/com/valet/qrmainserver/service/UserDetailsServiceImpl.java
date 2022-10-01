package com.valet.qrmainserver.service;

import com.valet.qrmainserver.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return repo.findByEmail(username);
        } catch (Exception e){
            e.printStackTrace();
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }
}
