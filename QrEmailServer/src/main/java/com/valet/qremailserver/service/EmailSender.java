package com.valet.qremailserver.service;

import com.valet.qremailserver.model.email.Email;

public interface EmailSender {
    void send(Email email);
}
