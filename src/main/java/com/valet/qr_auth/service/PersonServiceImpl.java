package com.valet.qr_auth.service;

import com.valet.qr_auth.model.Person;
import com.valet.qr_auth.repo.PersonRepo;
import com.valet.qr_auth.service.interfaces.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PasswordEncoder encoder;
    private final PersonRepo repo;

    @Override
    @Cacheable("user_exist")
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

    @Override
    public boolean changeOrganization(Person person, String organization) {

        if (organization == null || organization.length() == 0) {
            return false;
        }

        if (person.getOrganization().equals(organization)){
            return true;
        } else {
            String org = repo.changeOrganization(person.getId(), organization);
            return organization.equals(org);
        }
    }

    @Override
    public boolean changePhone(Person person, String phone) {
        if (phone == null || phone.length() == 0) {
            return false;
        }

        if (person.getOrganization().equals(phone)){
            return true;
        } else {
            String org = repo.changePhone(person.getId(), phone);
            return phone.equals(org);
        }
    }

    @Override
    public boolean changeName(Person person, String name) {
        if (name == null || name.length() == 0) {
            return false;
        }

        if (person.getOrganization().equals(name)){
            return true;
        } else {
            String org = repo.changeName(person.getId(), name);
            return name.equals(org);
        }
    }

    @Override
    public boolean deleteUser(Person person) {
        if (person != null){
            return repo.deleteUser(person.getId());
        } else {
            return false;
        }
    }

    @Override
    public boolean changePassword(Long id, String password) {
        return repo.changePassword(id, encoder.encode(password));
    }
}
