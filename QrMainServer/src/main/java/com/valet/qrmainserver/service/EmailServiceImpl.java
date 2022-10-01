package com.valet.qrmainserver.service;

import com.valet.qrmainserver.model.email.Email;
import com.valet.qrmainserver.service.interfaces.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final KafkaTemplate<String, Email> template;

    @Override
    public void sendEmail(String userId, Email email) {
            template.send("sendEmailTopic", email);
    }
}
