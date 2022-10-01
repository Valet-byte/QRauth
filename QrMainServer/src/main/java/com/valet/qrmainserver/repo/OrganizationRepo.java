package com.valet.qrmainserver.repo;

import com.valet.qrmainserver.model.Organization;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrganizationRepo extends MongoRepository<Organization, String> {
    //List<String> findName();

    Page<Organization> findAll (@NonNull Pageable pageable);
}
