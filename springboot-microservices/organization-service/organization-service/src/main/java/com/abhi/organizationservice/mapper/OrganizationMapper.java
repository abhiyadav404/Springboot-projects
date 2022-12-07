package com.abhi.organizationservice.mapper;

import com.abhi.organizationservice.dto.OrganizationDto;
import com.abhi.organizationservice.entity.Organization;

public class OrganizationMapper {

    public static OrganizationDto mapToOrganizationDto(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto(organization.getId()
                , organization.getOrganizationName()
                , organization.getOrganizationDescription()
                , organization.getOrganizationCode()
                , organization.getCreatedDate());

        return organizationDto;
    }

    public static Organization mapToOrganization(OrganizationDto organizationDto){
        Organization organization = new Organization(organizationDto.getId(),
                organizationDto.getOrganizationName(),
                organizationDto.getOrganizationDescription(),
                organizationDto.getOrganizationCode(),
                organizationDto.getCreatedDate());

        return organization;
    }
}
