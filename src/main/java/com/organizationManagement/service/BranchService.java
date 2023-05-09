package com.organizationManagement.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.organizationManagement.entity.Branch;
import com.organizationManagement.repository.BranchRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Service
public class BranchService {
	@Autowired
	private BranchRepository branchRepository;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private ObjectMapper objectMapper;

	public Branch createBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	public List<Branch> getAllDetails(Pageable pageable) {
		pageable.getPageNumber();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Branch> criteriaQuery = criteriaBuilder.createQuery(Branch.class);
		Root<Branch> root = criteriaQuery.from(Branch.class);
		criteriaQuery.select(root);
		TypedQuery<Branch> query = entityManager.createQuery(criteriaQuery);
		
		query.setFirstResult(pageable.getPageNumber());
		query.setMaxResults(pageable.getPageSize());
		return query.getResultList();

	}

	public Branch getDetailsById(int id) {
		Branch branch = branchRepository.findById(id).get();
		return branch;
	}

	public String deleteById(int id) {
		branchRepository.deleteById(id);
		return "details deleted successfully";
	}

	@Transactional
	public Branch updateDetails(int id, Branch inputbranch) {
		Branch dbBranch = null;
		Branch updatedBranch = null;
		dbBranch = branchRepository.findById(id).get();
		// entityManager.lock(branch, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (id != 0) {
			dbBranch.setBranchName(inputbranch.getBranchName());
			dbBranch.setBranchDetails(inputbranch.getBranchDetails());
			dbBranch.setOrganization(inputbranch.getOrganization());
			updatedBranch = branchRepository.save(dbBranch);
		}
		return updatedBranch;
	}

	public Branch patch(int id, JsonPatch jsonPatch)
			throws IllegalArgumentException, JsonPatchException, JsonProcessingException {
		Branch dbBranch = branchRepository.findById(id).get();
		JsonNode jsonNode = jsonPatch.apply(objectMapper.convertValue(dbBranch, JsonNode.class));
		Branch patched = objectMapper.treeToValue(jsonNode, Branch.class);

		return branchRepository.save(patched);
	}
}
