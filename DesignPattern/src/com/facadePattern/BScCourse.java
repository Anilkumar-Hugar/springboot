package com.facadePattern;

public class BScCourse implements Course {
	private String bsc;
	private String code="BS101";

	@Override
	public String getCourse() {
		System.out.println(code);
		return bsc;
	}

}
