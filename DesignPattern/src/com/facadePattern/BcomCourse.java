package com.facadePattern;

public class BcomCourse implements Course {
	private String Bcom;
	private String code="BCOM111";

	@Override
	public String getCourse() {
		System.out.println(code);
		return Bcom;
	}

}
