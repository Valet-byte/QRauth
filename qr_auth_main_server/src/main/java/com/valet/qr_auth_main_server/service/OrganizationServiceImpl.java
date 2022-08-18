package com.valet.qr_auth_main_server.service;

import com.valet.qr_auth_main_server.model.Organization;
import com.valet.qr_auth_main_server.repo.OrganizationRepo;
import com.valet.qr_auth_main_server.service.interfaces.ActionService;
import com.valet.qr_auth_main_server.service.interfaces.OrganizationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepo organizationRepo;

    @Autowired
    private ActionService actionService;

    @Override
    public Flux<String> getAllOrganizationName() {
        return organizationRepo.findName();
    }

    @Override
    public Mono<Organization> createOrganization(Long creatorId, String organizationName) {
        return organizationRepo.save(Organization.builder()
                .name(organizationName).creatorId(creatorId).build())
                .map(organization -> {
                    actionService.changeRoleUser(creatorId, 4);
                    return organization;
                }).map(organization -> {
                    actionService.fastOrganizationChange(creatorId, organization.getId());
                    return organization;
                });
    }

    @Override
    public Mono<Void> deleteOrganization(Long organizationId) {
        return null;
    }

    @Override
    public Mono<Void> deleteOrganization(String organizationName) {
        return null;
    }

    @Override
    public Mono<Long> getIdByName(String value) {
        return organizationRepo.findIdByName(value);
    }

}
