package com.valet.qr_auth_main_server.service;

import com.valet.qr_auth_main_server.model.AdminToUser;
import com.valet.qr_auth_main_server.model.User;
import com.valet.qr_auth_main_server.model.dto.UserDTO;
import com.valet.qr_auth_main_server.repo.AdminToUserRepo;
import com.valet.qr_auth_main_server.repo.UserRepo;
import com.valet.qr_auth_main_server.service.interfaces.CommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class CommandServiceImpl implements CommandService {

    private final UserRepo userRepo;
    private final AdminToUserRepo adminToUserRepo;

    @Override
    public Flux<UserDTO> getMyCommand(Long userId) {
        return userRepo.findAllById(adminToUserRepo.findAllByAdmin_id(userId).map(AdminToUser::getUser_id)).map(User::getDTO);

    }
}
