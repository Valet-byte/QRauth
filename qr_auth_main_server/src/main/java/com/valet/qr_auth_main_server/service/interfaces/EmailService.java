package com.valet.qr_auth_main_server.service.interfaces;

public interface EmailService {
    void sendSimpleEmail(String toAddress, String subject, String message);
}
