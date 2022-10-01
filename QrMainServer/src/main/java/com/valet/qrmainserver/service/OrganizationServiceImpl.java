package com.valet.qrmainserver.service;

import com.valet.qrmainserver.model.Organization;
import com.valet.qrmainserver.repo.OrganizationRepo;
import com.valet.qrmainserver.service.interfaces.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepo repo;

    @Override
    public Page<String> getOrganizationsName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return repo.findAll(pageable).map(Organization::getName);
    }
}
