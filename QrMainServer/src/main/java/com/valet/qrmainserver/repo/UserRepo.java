package com.valet.qrmainserver.repo;

import com.valet.qrmainserver.model.User;
import com.valet.qrmainserver.model.dto.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findByEmail(String email);
    UserDTO getById(String id);
    long countByEmail (String email);

}
