package com.valet.qr_registration_server;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class QrRegistrationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrRegistrationServerApplication.class, args);
	}

	@Bean
	public ObjectMapper mapper(){
		return new ObjectMapper();
	}



}
