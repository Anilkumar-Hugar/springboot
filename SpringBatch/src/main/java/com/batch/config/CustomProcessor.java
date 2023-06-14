package com.batch.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.batch.entity.Student;
@Component
public class CustomProcessor implements ItemProcessor<Student, Student> {

	@Override
	public Student process(Student student) throws Exception {
		if (student.getCourse().equals("mca")) {
			return student;
		} else
			return null;
	}

}
