package com.organizationManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.organizationManagement.entity.Organization;
import com.organizationManagement.entity.OrganizationKeyClass;
import com.organizationManagement.repository.OrganizationRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Service
public class OrganizationService {
	@Autowired
	private OrganizationRepository organizationRepository;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private ObjectMapper objectMapper;

	public Organization createOrganization(Organization organization) {
		return organizationRepository.save(organization);
	}

	public List<Organization> getDetails() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
		Root<Organization> root = criteriaQuery.from(Organization.class);
		criteriaQuery.select(root);
		TypedQuery<Organization> query = entityManager.createQuery(criteriaQuery);
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Organization> list = query.getResultList();
		return list;
	}

	public Organization getDetailsById(int id, String name) {
		OrganizationKeyClass organizationKeyClass = new OrganizationKeyClass(id, name);
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
		Root<Organization> root = criteriaQuery.from(Organization.class);
		List<Predicate> predicates = new ArrayList<>();
		if (organizationKeyClass.getOrganizationId() != 0) {
			Predicate organizationId = criteriaBuilder.equal(root.get("organizationKeyClass").get("organizationId"),
					organizationKeyClass.getOrganizationId());
			predicates.add(organizationId);
		}
		if (organizationKeyClass.getOrganizationName() != null) {
			Predicate organizationName = criteriaBuilder.like(root.get("organizationKeyClass").get("organizationName"),
					organizationKeyClass.getOrganizationName());
			predicates.add(organizationName);
		}

		criteriaQuery.where(criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])));

		return entityManager.createQuery(criteriaQuery).getSingleResult();
	}

	public ResponseEntity<String> deleteDetailsById(int id, String name) {
		OrganizationKeyClass organizationKeyClass = new OrganizationKeyClass(id, name);
		organizationRepository.deleteById(organizationKeyClass);
		return ResponseEntity.ok("details deleted ");
	}

	@Transactional
	public Organization updateById(int id, String name, Organization organization) {
		OrganizationKeyClass organizationKeyClass = new OrganizationKeyClass(id, name);
		Organization organizations = organizationRepository.findById(organizationKeyClass).get();
		if (organizationKeyClass != null) {
			organizations.setOrganizationKeyClass(organization.getOrganizationKeyClass());
			organizations.setOrganizationDetails(organization.getOrganizationDetails());
			organizations.setOrganizationDetails(organization.getOrganizationDetails());
		}
		return organizationRepository.save(organizations);
	}

	public Organization patch(int id, String name, JsonPatch jsonPatch)
			throws IllegalArgumentException, JsonPatchException, JsonProcessingException {
		OrganizationKeyClass organizationKeyClass = new OrganizationKeyClass(id, name);
		Organization organization = organizationRepository.findById(organizationKeyClass).get();
		JsonNode jsonNode = jsonPatch.apply(objectMapper.convertValue(organization, JsonNode.class));
		Organization patchedData = objectMapper.treeToValue(jsonNode, Organization.class);
		return organizationRepository.save(patchedData);
	}
}
