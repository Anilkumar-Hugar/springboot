package com.organizationManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.organizationManagement.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Integer>{

}
