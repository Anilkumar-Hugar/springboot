package com.organizationManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.organizationManagement.entity.Branch;
import com.organizationManagement.service.BranchService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/branch")
public class BranchController {
	@Autowired
	private BranchService branchService;

	@PostMapping
	@Operation(summary = "Create organization and save it to the database")
	public ResponseEntity<Branch> createBranch(@Valid @RequestBody Branch branch) {
		Branch branches = branchService.createBranch(branch);
		return ResponseEntity.ok(branches);
	}

	@GetMapping
	@Operation(summary = "Display all details")
	public ResponseEntity<List<Branch>> getAllDetails() {

		return ResponseEntity.ok(branchService.getAllDetails());
	}

	@GetMapping("/getById")
	@Operation(summary = "Display details based on Id")
	public ResponseEntity<Branch> getDetailsById(@Valid @RequestParam(name = "id") int id) {
		return ResponseEntity.ok(branchService.getDetailsById(id));
	}

	@DeleteMapping
	@Operation(summary = "Delete details based on Id")
	public ResponseEntity<String> deleteById(@Valid @RequestParam(name = "id") int id) {
		return ResponseEntity.ok(branchService.deleteById(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update details based on Id")
	public ResponseEntity<Branch> updateDetails(@Valid @RequestParam(name = "id") int id,@Valid @RequestBody Branch branch) {
		return ResponseEntity.ok(branchService.updateDetails(id, branch));
	}

	@PatchMapping(path = "/{id}",consumes = "application/json-patch+json")
	public ResponseEntity<Branch> update(@Valid @RequestParam(name = "id") int id, @Valid @RequestBody JsonPatch jsonPatch)
			throws JsonProcessingException, JsonPatchException {
		return ResponseEntity.ok(branchService.patch(id, jsonPatch));
	}
}
