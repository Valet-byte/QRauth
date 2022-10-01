package com.valet.qremailserver.service;

import com.valet.qremailserver.model.email.ActionType;
import com.valet.qremailserver.service.EmailCreator.EmailCreator;
import com.valet.qremailserver.model.email.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmailSenderImpl implements EmailSender {

    private final Map<ActionType, EmailCreator> creatorMap;
    private final JavaMailSender sender;

    private final String from;

    public EmailSenderImpl(List<EmailCreator> creators, JavaMailSender sender, @Value("${spring.mail.username}") String from) {
        creatorMap = creators.stream().collect(Collectors.toMap(EmailCreator::getMyType, emailCreator -> emailCreator));
        this.sender = sender;
        this.from = from;
    }

    @Override
    @Async
    public void send(Email email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(email.getEmail());
        mailMessage.setSubject(email.getSubject());
        mailMessage.setText(creatorMap.get(email.getType()).createEmail(email.getUsernames(), email.getLink()));
        sender.send(mailMessage);
    }
}
