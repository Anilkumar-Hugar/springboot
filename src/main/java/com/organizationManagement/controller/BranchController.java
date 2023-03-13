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

import com.organizationManagement.entity.Branch;
import com.organizationManagement.service.BranchService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/branch")
public class BranchController {
	@Autowired
	private BranchService branchService;

	@PostMapping
	@Operation(summary = "Create organization and save it to the database")
	public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) {
		Branch branches = branchService.createBranch(branch);
		return ResponseEntity.ok(branches);
	}

	@GetMapping
	@Operation(summary = "Display all details")
	public ResponseEntity<List<Branch>> getAllDetails() {
		return ResponseEntity.ok(branchService.getAllDetails());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Display details based on Id")
	public ResponseEntity<Branch> getDetailsById(@PathVariable int id) {
		return ResponseEntity.ok(branchService.getDetailsById(id));
	}

	@DeleteMapping
	@Operation(summary = "Delete details based on Id")
	public ResponseEntity<String> deleteById(int id) {
		return ResponseEntity.ok(branchService.deleteById(id));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update details based on Id")
	public ResponseEntity<Branch> updateDetails(@PathVariable int id, @RequestBody Branch branch) {
		return ResponseEntity.ok(branchService.updateDetails(id, branch));
	}

}
