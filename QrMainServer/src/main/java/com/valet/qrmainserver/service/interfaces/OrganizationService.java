package com.valet.qrmainserver.service.interfaces;

import org.springframework.data.domain.Page;

public interface OrganizationService {
    Page<String> getOrganizationsName(int page, int size);
}
