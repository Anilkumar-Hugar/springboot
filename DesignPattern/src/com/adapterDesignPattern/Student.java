package com.adapterDesignPattern;

public class Student {
	int rollNo;
	String course;

	public Student() {

	}

	public Student(int rollNo, String course) {
		super();
		this.rollNo = rollNo;
		this.course = course;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}
