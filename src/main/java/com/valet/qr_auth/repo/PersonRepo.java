package com.valet.qr_auth.repo;

import com.valet.qr_auth.model.Person;

public interface PersonRepo {
    Person findByName(String name);
    Person findByPhone(String phone);
    Person save(Person person);
    Person findById (Long id);

    boolean existUser(String phone);
    String changeOrganization(Long id, String organization);

    String changePhone(Long id, String phone);

    String changeName(Long id, String name);

    boolean deleteUser(Long id);

    boolean changePassword(Long id, String encodePass);
}
