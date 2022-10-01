package com.valet.qremailserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class QrEmailServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QrEmailServerApplication.class, args);
    }

}
