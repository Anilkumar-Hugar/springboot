package com.aop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aop.entity.Person;

public interface PersonRepo extends JpaRepository<Person, Integer> {

}
