package com.prototypepattern;

public class PersonImpl extends Person {
	int id;
	String name;
	String city;

	public PersonImpl() {

	}

	public PersonImpl(int id, String name, String city) {
		super();
		this.id = id;
		this.name = name;
		this.city = city;
	}

	public String showData() {
		return "PersonImpl [id=" + id + ", name=" + name + ", city=" + city + "]";
	}

	@Override
	public Person getClone() {
		return new PersonImpl(id, name, city);
	}
}
