package com.valet.qr_auth.service.interfaces;

import com.valet.qr_auth.model.Person;

public interface PersonService {
    boolean existUser(String phone);

    Person save(Person person);

    boolean changeOrganization(Person person, String organization);
    boolean changePhone(Person person, String phone);
    boolean changeName(Person person, String name);

    boolean deleteUser(Person person);

    boolean changePassword(Long id, String password);
}
