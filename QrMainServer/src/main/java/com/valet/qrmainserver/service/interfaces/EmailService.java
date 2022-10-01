package com.valet.qrmainserver.service.interfaces;

import com.valet.qrmainserver.model.email.Email;

public interface EmailService {
    void sendEmail(String userId, Email email);
}
