package com.adapterDesignPattern;

import java.util.Scanner;

public class StudentImpl extends Student implements Library {

	@Override
	public void giveDetails() {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.println("enter the student roll no: ");
			int id = Integer.parseInt(scanner.nextLine());
			System.out.println("enter the student course: ");
			String course = scanner.nextLine();
			setRollNo(id);
			setCourse(course);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getBooks() {
		int id = getRollNo();
		String course = getCourse();
		return ("The student with roll no " + id + " is eligible and proivide the books for course " + course);
	}

}
