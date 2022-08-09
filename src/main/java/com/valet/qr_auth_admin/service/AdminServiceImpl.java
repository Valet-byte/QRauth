package com.valet.qr_auth_admin.service;

import com.valet.qr_auth_admin.model.Admin;
import com.valet.qr_auth_admin.repo.AdminRepo;
import com.valet.qr_auth_admin.service.interfaces.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepo repo;
    private final PasswordEncoder encoder;

    @Override
    public Mono<Admin> registration(Admin admin) {
        admin.setPassword(encoder.encode(admin.getPassword()));
        return repo.save(admin).map(a -> {a.setPassword("NONE"); return a;});
    }


    @Override
    public Mono<Void> delete(Admin admin) {
        return null;
    }
}
