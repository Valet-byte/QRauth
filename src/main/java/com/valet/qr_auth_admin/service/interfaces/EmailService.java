package com.valet.qr_auth_admin.service.interfaces;

public interface EmailService {
    void sendSimpleEmail(String toAddress, String subject, String message);
}
