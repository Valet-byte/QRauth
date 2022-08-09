package com.valet.qr_auth_admin.service.interfaces;

import com.valet.qr_auth_admin.model.Admin;
import reactor.core.publisher.Mono;

public interface AdminService {
    Mono<Admin> registration(Admin admin);
    Mono<Void> delete(Admin admin);
}
