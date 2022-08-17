package com.valet.qr_email_server.controller;

import com.valet.qr_email_server.service.MailSender;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class MailController {

    private final MailSender sender;

    @PostMapping("/send")
    public Mono<Boolean> send(
            @RequestParam String toAddress,
            @RequestParam String subject,
            @RequestParam String message
    ){
        System.out.println(toAddress + subject + message);
        sender.mailSend(toAddress, subject, message);
        return Mono.just(true);
    }

}
