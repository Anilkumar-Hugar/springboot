package com.aop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aop.entity.Person;
import com.aop.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

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
	public List<Person> getAll() {
		return service.getAll();
	}

	@GetMapping("/getById")
	public Optional<Person> findById(@RequestParam(name = "id") int id) {
		return service.findById(id);
	}

	@DeleteMapping("/delete")
	public String deleteById(@RequestParam(name="id") int id) {
		return service.deleteById(id);
	}

	@PatchMapping("/update/{id}")
	public Person update(@RequestParam(name = "id") int id, @RequestBody JsonPatch jsonPatch)
			throws JsonProcessingException, IllegalArgumentException, JsonPatchException {
		return service.patch(id, jsonPatch);
	}
}
