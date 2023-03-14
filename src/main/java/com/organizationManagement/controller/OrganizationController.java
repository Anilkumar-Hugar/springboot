package com.organizationManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.organizationManagement.entity.Organization;
import com.organizationManagement.service.OrganizationService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
	@Autowired
	private OrganizationService organizationService;

	@PostMapping
	@Operation(summary = "Create organization and save to database")
	public ResponseEntity<Organization> createOrganization(@Valid @RequestBody Organization organization) {
		Organization organizations = organizationService.createOrganization(organization);
		return ResponseEntity.ok(organizations);
	}

	@GetMapping
	@Operation(summary = "Display all details")
	public ResponseEntity<List<Organization>> getDetails() {
		List<Organization> organizations = organizationService.getDetails();
		return ResponseEntity.ok(organizations);
	}

	@GetMapping("/getById")
	@Operation(summary = "Display details based on id and name")
	public ResponseEntity<Organization> getDetailsById(@RequestParam(name = "id") int id, @RequestParam(name="name") String name) {
		Organization organization = organizationService.getDetailsById(id, name);
		return ResponseEntity.ok(organization);
	}

	@DeleteMapping
	@Operation(summary = "Delete details based on Id and Name")
	public ResponseEntity<String> deleteDetailsById(@RequestParam(name="id") int id,@RequestParam(name="name") String name) {
		return organizationService.deleteDetailsById(id,name);
	}

	@PutMapping
	@Operation(summary = "Update details based on Id and Name")
	public ResponseEntity<Organization> updateById(@RequestParam(name = "id") int id, @RequestParam(name = "name") String name,
			@RequestBody Organization organization) {
		return ResponseEntity.ok(organizationService.updateById(id, name, organization));
	}
}
