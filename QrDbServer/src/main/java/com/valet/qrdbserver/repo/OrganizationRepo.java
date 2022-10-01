package com.valet.qrdbserver.repo;

import com.valet.qrdbserver.model.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepo extends MongoRepository<Organization, String> {
}
