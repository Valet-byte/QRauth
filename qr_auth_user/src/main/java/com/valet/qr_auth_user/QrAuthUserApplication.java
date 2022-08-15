package com.valet.qr_auth_user;

import com.valet.qr_auth_user.model.User;
import com.valet.qr_auth_user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class QrAuthUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrAuthUserApplication.class, args);
	}

}
