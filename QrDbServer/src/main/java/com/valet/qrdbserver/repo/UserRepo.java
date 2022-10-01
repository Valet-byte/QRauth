package com.valet.qrdbserver.repo;

import com.valet.qrdbserver.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {

}
