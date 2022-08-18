package com.valet.qr_auth_main_server.service;

import com.valet.qr_auth_main_server.service.interfaces.EmailService;
import lombok.AllArgsConstructor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final WebClient.Builder builder;

    @Override
    public void sendSimpleEmail(String toAddress, String subject, String message) {
        builder.build()
                .post().uri("http://EMAIL/send?toAddress=" + toAddress + "&subject=" + subject +
                        "&message=" + message)

                .retrieve()
                .bodyToMono(Boolean.class).log().subscribe(System.out::println);
    }
}
