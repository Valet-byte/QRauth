package com.valet.qr_auth_admin.service;

import com.valet.qr_auth_admin.model.changeAction.Action;
import com.valet.qr_auth_admin.model.changeAction.ActionType;
import com.valet.qr_auth_admin.repo.ActionRedisRepo;
import com.valet.qr_auth_admin.repo.AdminRepo;
import com.valet.qr_auth_admin.service.interfaces.ActionService;
import com.valet.qr_auth_admin.service.interfaces.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRedisRepo repo;
    private final PasswordEncoder encoder;
    private final AdminRepo adminRepo;
    private final EmailService emailService;

    @Override
    public boolean changePassword(Long userId, String userEmail, String password) {
        try {
            Action action = repo.save(Action.builder()
                    .userId(userId)
                    .value(encoder.encode(password))
                    .actionType(ActionType.CHANGE_PASSWORD).build());

            emailService.sendSimpleEmail(userEmail, "Change password", "http://localhost:9630/do/" + action.getId());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changeJobTitle(Long userId, String userEmail, String jobTitle) {
        try {
            Action action = repo.save(Action.builder()
                    .userId(userId)
                    .value(jobTitle)
                    .actionType(ActionType.CHANGE_JOB_TITLE).build());
            emailService.sendSimpleEmail(userEmail, "Change job_title", "http://localhost:9630/do/" + action.getId());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changeName(Long userId, String userEmail, String name) {
        try {
            Action action = repo.save(Action.builder()
                    .userId(userId)
                    .value(name)
                    .actionType(ActionType.CHANGE_USERNAME).build());
            emailService.sendSimpleEmail(userEmail, "Change username", "http://localhost:9630/do/" + action.getId());
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean doAction(String actionId) {

        try {
            Action action = repo.findById(actionId).get();
            repo.deleteById(actionId);

            switch (action.getActionType()){
                case CHANGE_USERNAME -> adminRepo.changeNameById(action.getUserId(), action.getValue()).subscribe(System.out::println);
                case CHANGE_JOB_TITLE -> adminRepo.changeJobTitleById(action.getValue(), action.getUserId()).subscribe(System.out::println);
                case CHANGE_PASSWORD -> adminRepo.changePasswordById(action.getUserId(), action.getValue()).subscribe(System.out::println);
            }

            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
