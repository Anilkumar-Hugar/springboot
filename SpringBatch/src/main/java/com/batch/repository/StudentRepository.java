package com.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.batch.entity.Student;

public interface StudentRepository extends JpaRepository<Student, String>{

}
