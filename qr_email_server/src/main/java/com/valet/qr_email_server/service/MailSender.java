package com.valet.qr_email_server.service;

public interface MailSender {
    void mailSend(String toAddress, String subject, String message);
}
