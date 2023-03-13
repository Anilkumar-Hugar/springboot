package com.organizationManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizationManagement.entity.Organization;
import com.organizationManagement.entity.OrganizationKeyClass;

public interface OrganizationRepository extends JpaRepository<Organization, OrganizationKeyClass>{

}
