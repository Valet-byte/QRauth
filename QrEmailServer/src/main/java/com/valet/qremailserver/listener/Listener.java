package com.valet.qremailserver.listener;

import com.valet.qremailserver.model.email.Email;
import com.valet.qremailserver.service.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Listener {

    private final EmailSender sender;

    @KafkaListener(topics = "sendEmailTopic")
    public void sendEmail(@Payload Email email){
        sender.send(email);
    }
}
