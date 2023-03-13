package com.organizationManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.organizationManagement.entity.Organization;
import com.organizationManagement.entity.OrganizationKeyClass;
import com.organizationManagement.service.OrganizationService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
	@Autowired
	private OrganizationService organizationService;

	@PostMapping
	@Operation(summary = "Create organization and save to database")
	public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
		Organization organizations = organizationService.createOrganization(organization);
		return ResponseEntity.ok(organizations);
	}

	@GetMapping
	@Operation(summary = "Display all details")
	public ResponseEntity<List<Organization>> getDetails() {
		List<Organization> organizations = organizationService.getDetails();
		return ResponseEntity.ok(organizations);
	}

	@GetMapping("/{id}/{name}")
	@Operation(summary = "Display details based on id and name")
	public ResponseEntity<Organization> getDetailsById(@PathVariable int id, @PathVariable String name) {
		Organization organization = organizationService.getDetailsById(id, name);
		return ResponseEntity.ok(organization);
	}

	@DeleteMapping
	@Operation(summary = "Delete details based on Id and Name")
	public ResponseEntity<String> deleteDetailsById(@RequestBody OrganizationKeyClass organizationKeyClass) {
		return organizationService.deleteDetailsById(organizationKeyClass);
	}

	@PutMapping("/{id}/{name}")
	@Operation(summary = "Update details based on Id and Name")
	public ResponseEntity<Organization> updateById(@PathVariable int id, @PathVariable String name,
			@RequestBody Organization organization) {
		return ResponseEntity.ok(organizationService.updateById(id, name, organization));
	}
}
