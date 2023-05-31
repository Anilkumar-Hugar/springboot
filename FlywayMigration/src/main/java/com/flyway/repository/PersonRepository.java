package com.flyway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyway.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
