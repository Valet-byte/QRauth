package com.valet.qr_auth_user.service.interfaces;

public interface EmailService {
    void sendSimpleEmail(String toAddress, String subject, String message);
}
