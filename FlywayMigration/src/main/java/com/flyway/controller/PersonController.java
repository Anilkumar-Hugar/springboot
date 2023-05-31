package com.flyway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flyway.entity.Person;
import com.flyway.service.PersonService;

@RestController
@RequestMapping("/api")
public class PersonController {
	@Autowired
	private PersonService service;

	@PostMapping("/create")
	public Person createPerson(@RequestBody Person person) {
		return service.createPerson(person);
	}

	@GetMapping("/getAll")
	public List<Person> findAll() {
		return service.findAll();
	}

	@GetMapping("/get")
	public Person findById(@RequestParam(name = "id") int id) {
		return service.findById(id);
	}
}
