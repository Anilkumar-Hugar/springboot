package com.organizationManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organizationManagement.entity.Branch;
import com.organizationManagement.repository.BranchRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
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

	public Branch createBranch(Branch branch) {
		return branchRepository.save(branch);
	}

	public List<Branch> getAllDetails() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Branch> criteriaQuery = criteriaBuilder.createQuery(Branch.class);
		Root<Branch> root = criteriaQuery.from(Branch.class);
		criteriaQuery.select(root);
		TypedQuery<Branch> query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(0);
		query.setMaxResults(5);
		return query.getResultList();
	}

	public Branch getDetailsById(int id) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Branch> criteriaQuery = criteriaBuilder.createQuery(Branch.class);
		Root<Branch> root = criteriaQuery.from(Branch.class);
		if (id != 0) {
			criteriaQuery.where(criteriaBuilder.equal(root.get("branchId"), id));

		} else
			throw new IllegalArgumentException("entered id is invalid");
		Branch branch = entityManager.createQuery(criteriaQuery).getSingleResult();
		return branch;
	}

	public String deleteById(int id) {
		branchRepository.deleteById(id);
		return "details deleted successfully";
	}

	@Transactional
	public Branch updateDetails(int id, Branch branch) {
		Branch branches = null;
		branches = branchRepository.findById(id).get();
		// entityManager.lock(branch, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if (id != 0) {
			branches.setBranchName(branch.getBranchName());
			branches.setBranchDetails(branch.getBranchDetails());
			branches.setOrganization(branch.getOrganization());
		} else {
			throw new IllegalArgumentException("Entered id is not available");
		}
		return branches;
	}
}
