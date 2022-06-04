package com.valet.qr_auth.service.interfaces;

import com.valet.qr_auth.model.Person;

public interface PersonService {
    boolean existUser(String phone);

    Person save(Person person);
}
