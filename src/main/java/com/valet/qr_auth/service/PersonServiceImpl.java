package com.valet.qr_auth.service;

import com.valet.qr_auth.model.Person;
import com.valet.qr_auth.repo.PersonRepo;
import com.valet.qr_auth.service.interfaces.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PasswordEncoder encoder;
    private final PersonRepo repo;

    @Override
    public boolean existUser(String phone) {
        try {
            return repo.existUser(phone);
        } catch (EmptyResultDataAccessException e){
            return false;
        }

    }

    @Override
    public Person save(Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        Person person1 = repo.save(person);
        person1.setPassword("NONE");
        return person1;
    }
}
