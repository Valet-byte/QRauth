package com.valet.qrdbserver.repo;

import com.valet.qrdbserver.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepo extends MongoRepository<Team, String> {
}
