package com.valet.qrmainserver.repo;

import com.valet.qrmainserver.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepo extends MongoRepository<Team, String> {
    Team getByAdminId (String adminId);
}
