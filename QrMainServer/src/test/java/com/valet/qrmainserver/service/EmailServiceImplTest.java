package com.valet.qrmainserver.service;

import com.valet.qrmainserver.model.User;
import com.valet.qrmainserver.model.action.ActionType;
import com.valet.qrmainserver.model.email.Email;
import com.valet.qrmainserver.repo.UserRepo;
import com.valet.qrmainserver.service.interfaces.EmailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;

@SpringBootTest
class EmailServiceImplTest {

    @MockBean
    UserRepo mockRepo;

    @Autowired
    KafkaTemplate<String, Email> template;

    @Test
    @DisplayName("Send email test")
    void sendEmailTest(){
        Email email =  new Email(null, "Hello", null, null, ActionType.HELLO_EMAIL);
        Mockito.when(mockRepo.findById("TestId")).thenReturn(Optional.of(User.builder()
                .email("itcvantum@gmail.com")
                .name("1111")
                .build()));


        EmailService emailService = new EmailServiceImpl(template);
        emailService.sendEmail("TestId", email);
    }
}