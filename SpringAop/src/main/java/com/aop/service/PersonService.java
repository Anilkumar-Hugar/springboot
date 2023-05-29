package com.aop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aop.entity.Person;
import com.aop.repository.PersonRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@Service
public class PersonService {
	@Autowired
	private PersonRepo repo;
	@Autowired
	private ObjectMapper objectMapper;

	public Person createPerson(Person person) {
		return repo.save(person);
	}

	public List<Person> getAll() {
		return repo.findAll();
	}

	public Optional<Person> findById(int id) {
		return repo.findById(id);
	}

	public String deleteById(int id) {
		repo.deleteById(id);
		return "Data deleted successfully!";
	}

	public Person patch(int id, JsonPatch jsonPatch)
			throws IllegalArgumentException, JsonPatchException, JsonProcessingException {
		Optional<Person> person = repo.findById(id);
		if (person.isPresent()) {
			JsonNode jsonNode = jsonPatch.apply(objectMapper.convertValue(person, JsonNode.class));
			Person updatedData = objectMapper.treeToValue(jsonNode, Person.class);
			return repo.save(updatedData);
		}
		return null;
	}
}
