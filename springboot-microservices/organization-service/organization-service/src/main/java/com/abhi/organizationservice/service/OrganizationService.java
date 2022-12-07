package com.abhi.organizationservice.service;

import com.abhi.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
   OrganizationDto saveOrganization(OrganizationDto organizationDto);

   OrganizationDto getOrganizationByCode(String organizationCode);
}