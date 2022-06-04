package com.valet.qr_auth.repo;

import com.valet.qr_auth.model.Person;

public interface PersonRepo {
    Person findByName(String name);
    Person findByPhone(String phone);
    Person save(Person person);
    Person findById (Long id);

    boolean existUser(String phone);
}
