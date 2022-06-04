package com.valet.qr_auth.service;

import com.valet.qr_auth.repo.PersonRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return repo.findByPhone(username);
        } catch (Exception e){
            throw new UsernameNotFoundException(username + "  not found ---------------------------------");
        }
    }
}
