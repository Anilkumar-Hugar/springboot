package com.flyway;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flyway.entity.Person;
import com.flyway.service.PersonService;

@SpringBootTest
class FlywayMigrationApplicationTests {
	@Autowired
	private PersonService personService;
	
	@Test
	 void testCreatePerson() {
		Person person = new Person();
		person.setId(1);
		person.setCity("hyd");
		person.setName("sunil");
		person.setPincode("123456");
		person.setEmail("sunil@gmail.com");
		Person per = personService.createPerson(person);
		assertThat(per).isNotNull();
		assertEquals(1,per.getId());
	}

	@Test
	 void testGetAll() {
		List<Person> person = personService.findAll();
		assertThat(personService.findAll()).isNotEmpty();
		assertEquals(person, personService.findAll());
	}

	@Test
	 void testGetById() {
		Person person = personService.findById(1);
		assertEquals(1, person.getId());
		assertThat(person).isNotNull();

	}

}
