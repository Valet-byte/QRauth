package com.valet.qr_auth_main_server.service;

import com.valet.qr_auth_main_server.service.interfaces.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final WebClient.Builder builder;

    @Override
    public void sendSimpleEmail(String toAddress, String subject, String message) {
        System.out.println("-----------------------------------------------------------------");
        builder.build()
                .post().uri("http://EMAIL/send")
                .attribute("toAddress", toAddress)
                .attribute("subject", subject)
                .attribute("message", message)
                .retrieve()
                .bodyToMono(Boolean.class).log().subscribe(System.out::println);
    }
}
