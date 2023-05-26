package com.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
	int id;
	String name;
	String city;
}
