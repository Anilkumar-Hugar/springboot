package com.flyway.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyway.entity.Person;
import com.flyway.repository.PersonRepository;

@Service
public class PersonService {
	@Autowired
	private PersonRepository repository;

	public Person createPerson(Person person) {
		return repository.save(person);
	}

	public List<Person> findAll() {
		return repository.findAll();
	}

	public Person findById(int id) {
		Person result = null;
		Optional<Person> person = repository.findById(id);
		if (person.isPresent()) {
			result = person.get();
		}
		return result;
	}
}
